package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.Role;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.CoupleStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.NumbersStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RatingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RolesHistoryStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.SerialityStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.VisitingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IVkService;
import com.vk.api.sdk.objects.base.Sex;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

    private final IVkService vkService;

    private final IPlayerDao playerDao;

    @Override
    public List<Player> getPlayers() {
        return playerDao.findAllByGamesTotalNotNull();
    }

    @Override
    public void savePlayers(List<Statistics> statistics) {
        if (statistics.isEmpty()) {
            return;
        }

        String statisticsSimpleName = statistics.get(0).getClass().getSimpleName();

        switch (statisticsSimpleName) {
            case "NumbersStatisticsAll":
                savePlayersFromNumbersStatistics(statistics);
                break;
            case "CoupleStatisticsAll":
                savePlayersFromCoupleStatistics(statistics);
                break;
            case "RatingStatisticsAll":
                savePlayersFromRatingStatistics(statistics);
                break;
            case "RolesHistoryStatisticsAll":
                savePlayersFromRolesHistoryStatistics(statistics);
                break;
            case "VisitingStatisticsAll":
                savePlayersFromVisitingStatistics(statistics);
                break;
            case "SerialityStatisticsAll":
                savePlayersFromSerialityStatistics(statistics);
                break;
        }
    }

    @Override
    public Player getPlayerById(Long id) {
        Player player = playerDao.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player", "id", id)
                );

        return getPlayer(player);
    }

    @Override
    public Player getPlayerByNickname(String nickname) {
        Player player = Optional.ofNullable(playerDao.findByNickname(nickname))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player", "nickname", nickname)
                );

        return getPlayer(player);
    }

    public Player getPlayer(Player player) {
        if (player.getVkId() != null) {
            if (player.getPhotoUrl() == null) {
                String vkPhoto = vkService.getPhotoByUserId(player.getVkId());
                player.setPhotoUrl(vkPhoto);
            }

            if (player.getGender() == null) {
                Sex vkGender = vkService.getGenderByUserId(player.getVkId());
                player.setGender(vkGender);
            }

            playerDao.save(player);
        }

        return player;
    }

    private void savePlayersFromNumbersStatistics(List<Statistics> numbersStatistics) {
        numbersStatistics.forEach(statisticsRow -> {
            NumbersStatisticsAll row = (NumbersStatisticsAll) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayersFromCoupleStatistics(List<Statistics> coupleStatistics) {
        Set<String> couplePlayers = new HashSet<>();
        coupleStatistics.forEach(statisticsRow -> {
            CoupleStatisticsAll row = (CoupleStatisticsAll) statisticsRow;

            couplePlayers.add(row.getNicknameOfMafiaOne());
            couplePlayers.add(row.getNicknameOfMafiaTwo());
        });

        couplePlayers.forEach(this::savePlayer);
    }

    private void savePlayersFromRatingStatistics(List<Statistics> ratingStatistics) {
        ratingStatistics.forEach(statisticsRow -> {
            RatingStatisticsAll row = (RatingStatisticsAll) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayersFromRolesHistoryStatistics(List<Statistics> rolesHistoryStatistics) {
        rolesHistoryStatistics.forEach(statisticsRow -> {
            RolesHistoryStatisticsAll row = (RolesHistoryStatisticsAll) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayersFromVisitingStatistics(List<Statistics> visitingStatistics) {
        visitingStatistics.forEach(statisticsRow -> {
            VisitingStatisticsAll row = (VisitingStatisticsAll) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayersFromSerialityStatistics(List<Statistics> serialityStatistics) {
        serialityStatistics.forEach(statisticsRow -> {
            SerialityStatisticsAll row = (SerialityStatisticsAll) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayer(String playerNickname) {
        if (!playerDao.existsByNickname(playerNickname)) {
            Player player = new Player();
            player.setNickname(playerNickname);
            player.setRoles(new HashSet<>(List.of(new Role("USER"))));

            playerDao.save(player);
        }
    }
}
