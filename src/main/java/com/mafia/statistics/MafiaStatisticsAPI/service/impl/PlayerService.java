package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.RoleDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.CoupleStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.NumbersStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RatingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RolesHistoryStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.SerialityStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.VisitingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IVkService;
import com.vk.api.sdk.objects.base.Sex;

import org.springframework.data.domain.PageRequest;
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
    public List<PlayerDto> getPlayers() {
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
    public List<Player> searchPlayersByNickname(String query) {
        return playerDao.findByNicknameFree(
                query.strip(),
                PageRequest.of(0, 15)
        );
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        PlayerDto player = playerDao.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player", "id", id)
                );

        return getPlayer(player);
    }

    @Override
    public PlayerDto getPlayerByNickname(String nickname) {
        PlayerDto player = Optional.ofNullable(playerDao.findByNickname(nickname))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player", "nickname", nickname)
                );

        return getPlayer(player);
    }

    public PlayerDto getPlayer(PlayerDto player) {
        if (player.getVkId() != null) {
            if (player.getPhotoUrl() == null || player.getPhotoUrl().equals("")) {
                String vkPhoto = vkService.getPhotoByUserId(player.getVkId());
                player.setPhotoUrl(vkPhoto);
            }

            if (player.getGender() == null || player.getGender().equals(Sex.UNKNOWN)) {
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
            PlayerDto player = new PlayerDto();
            player.setNickname(playerNickname);
            player.setCustomNickname(playerNickname);
            player.setGender(Sex.UNKNOWN);
            player.setRoles(new HashSet<>(List.of(new RoleDto("USER"))));

            playerDao.save(player);
        }
    }
}
