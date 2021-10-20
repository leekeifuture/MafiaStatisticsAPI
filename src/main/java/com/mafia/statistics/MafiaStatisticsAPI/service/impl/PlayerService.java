package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.exception.PlayerNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IVkService;
import com.vk.api.sdk.objects.base.Sex;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return playerDao.findAll();
    }

    @Override
    public void savePlayers(List<Statistics> statistics) {
        if (statistics.isEmpty()) {
            return;
        }

        String statisticsSimpleName = statistics.get(0).getClass().getSimpleName();

        switch (statisticsSimpleName) {
            case "NumbersStatistics":
                savePlayersFromNumbersStatistics(statistics);
                break;
            case "CoupleStatistics":
                savePlayersFromCoupleStatistics(statistics);
                break;
            case "RatingStatistics":
                savePlayersFromRatingStatistics(statistics);
                break;
            case "RolesHistoryStatistics":
                savePlayersFromRolesHistoryStatistics(statistics);
                break;
            case "VisitingStatistics":
                savePlayersFromVisitingStatistics(statistics);
                break;
            case "SerialityStatistics":
                savePlayersFromSerialityStatistics(statistics);
                break;
        }
    }

    @Override
    public Player getPlayerById(Long id) throws PlayerNotFoundException {
        Optional<Player> optPlayer = playerDao.findById(id);

        if (optPlayer.isEmpty()) {
            throw new PlayerNotFoundException(
                    String.format("Player with ID %s not found", id)
            );
        }

        Player player = optPlayer.get();

        if (player.getVkId() != null) {
            String vkPhoto = vkService.getPhotoByUserId(player.getVkId());
            if (player.getPhotoUrl() == null) {
                player.setPhotoUrl(vkPhoto);
            } else {
                if (!player.getPhotoUrl().equals(vkPhoto)) {
                    player.setPhotoUrl(vkPhoto);
                }
            }

            Sex vkGender = vkService.getGenderByUserId(player.getVkId());
            if (player.getGender() == null) {
                player.setGender(vkGender);
            } else {
                if (!player.getGender().equals(vkGender)) {
                    player.setGender(vkGender);
                }
            }

            playerDao.save(player);
        }

        return player;
    }

    private void savePlayersFromNumbersStatistics(List<Statistics> numbersStatistics) {
        numbersStatistics.forEach(statisticsRow -> {
            NumbersStatistics row = (NumbersStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });

    }

    private void savePlayersFromCoupleStatistics(List<Statistics> coupleStatistics) {
        Set<String> couplePlayers = new HashSet<>();
        coupleStatistics.forEach(statisticsRow -> {
            CoupleStatistics row = (CoupleStatistics) statisticsRow;

            couplePlayers.add(row.getNicknameOfMafiaOne());
            couplePlayers.add(row.getNicknameOfMafiaTwo());
        });

        couplePlayers.forEach(this::savePlayer);
    }

    private void savePlayersFromRatingStatistics(List<Statistics> ratingStatistics) {
        ratingStatistics.forEach(statisticsRow -> {
            RatingStatistics row = (RatingStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayersFromRolesHistoryStatistics(List<Statistics> rolesHistoryStatistics) {
        rolesHistoryStatistics.forEach(statisticsRow -> {
            RolesHistoryStatistics row = (RolesHistoryStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayersFromVisitingStatistics(List<Statistics> visitingStatistics) {
        visitingStatistics.forEach(statisticsRow -> {
            VisitingStatistics row = (VisitingStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayersFromSerialityStatistics(List<Statistics> serialityStatistics) {
        serialityStatistics.forEach(statisticsRow -> {
            SerialityStatistics row = (SerialityStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            savePlayer(playerNickname);
        });
    }

    private void savePlayer(String playerNickname) {
        if (!playerDao.existsByNickname(playerNickname)) {
            Player player = new Player();
            player.setNickname(playerNickname);

            playerDao.save(player);
        }
    }
}
