package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.CoupleStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RatingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.SerialityStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.VisitingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService implements IPlayerService {

    private final IPlayerDao playerDao;

    @Override
    public List<Player> getPlayers() {
        return playerDao.findAll();
    }

    @Override
    public List<Player> savePlayers(List<Statistics> statistics) {
        if (statistics.isEmpty()) {
            return new ArrayList<>();
        }

        String statisticsSimpleName = statistics.get(0).getClass().getSimpleName();

        switch (statisticsSimpleName) {
            case "NumbersStatistics":
                return savePlayersFromNumbersStatistics(statistics);
            case "CoupleStatistics":
                return savePlayersFromCoupleStatistics(statistics);
            case "RatingStatistics":
                return savePlayersFromRatingStatistics(statistics);
            case "RolesHistoryStatistics":
                return savePlayersFromRolesHistoryStatistics(statistics);
            case "VisitingStatistics":
                return savePlayersFromVisitingStatistics(statistics);
            case "SerialityStatistics":
                return savePlayersFromSerialityStatistics(statistics);
            default:
                return new ArrayList<>();
        }
    }

    private List<Player> savePlayersFromNumbersStatistics(List<Statistics> numbersStatistics) {
        List<Player> savedPlayers = new ArrayList<>();

        numbersStatistics.forEach(statisticsRow -> {
            NumbersStatistics row = (NumbersStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            if (!playerDao.existsByNickname(playerNickname)) {
                Player player = new Player();
                player.setNickname(playerNickname);
                player.setGamesTotal(row.getGamesTotal());
                player.setNumbersStatistics(row);

                playerDao.save(player);
                savedPlayers.add(player);
            } else if (playerDao.findByNickname(playerNickname).getNumbersStatistics() == null) {
                Player player = playerDao.findByNickname(playerNickname);
                player.setNumbersStatistics(row);

                playerDao.save(player);
            }
        });
        return savedPlayers;
    }

    private List<Player> savePlayersFromCoupleStatistics(List<Statistics> coupleStatistics) {
        List<Player> savedPlayers = new ArrayList<>();

        Set<String> couplePlayers = new HashSet<>();
        coupleStatistics.forEach(statisticsRow -> {
            CoupleStatistics row = (CoupleStatistics) statisticsRow;

            couplePlayers.add(row.getNicknameOfMafiaOne());
            couplePlayers.add(row.getNicknameOfMafiaTwo());
        });

        couplePlayers.forEach(playerNickname -> {
            List<CoupleStatistics> coupleStatisticsList = new ArrayList<>();
            coupleStatistics.forEach(statisticsRow -> {
                CoupleStatistics row = (CoupleStatistics) statisticsRow;

                if (row.getNicknameOfMafiaOne().equals(playerNickname) ||
                        row.getNicknameOfMafiaTwo().equals(playerNickname)) {
                    coupleStatisticsList.add(row);
                }
            });

            if (!playerDao.existsByNickname(playerNickname)) {
                Player player = new Player();
                player.setNickname(playerNickname);
                player.setCoupleStatistics(coupleStatisticsList);

                playerDao.save(player);
                savedPlayers.add(player);
            } else if (playerDao.findByNickname(playerNickname).getCoupleStatistics() == null) {
                Player player = playerDao.findByNickname(playerNickname);
                player.setCoupleStatistics(coupleStatisticsList);

                playerDao.save(player);
            }
        });
        return savedPlayers;
    }

    private List<Player> savePlayersFromRatingStatistics(List<Statistics> ratingStatistics) {
        List<Player> savedPlayers = new ArrayList<>();

        ratingStatistics.forEach(statisticsRow -> {
            RatingStatistics row = (RatingStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            if (!playerDao.existsByNickname(playerNickname)) {
                Player player = new Player();
                player.setNickname(playerNickname);
                player.setGamesTotal(row.getGamesTotal());
                player.setRatingStatistics(row);

                playerDao.save(player);
                savedPlayers.add(player);
            } else if (playerDao.findByNickname(playerNickname).getRatingStatistics() == null) {
                Player player = playerDao.findByNickname(playerNickname);
                player.setRatingStatistics(row);

                playerDao.save(player);
            }
        });
        return savedPlayers;
    }

    private List<Player> savePlayersFromRolesHistoryStatistics(List<Statistics> rolesHistoryStatistics) {
        List<Player> savedPlayers = new ArrayList<>();

        rolesHistoryStatistics.forEach(statisticsRow -> {
            RolesHistoryStatistics row = (RolesHistoryStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            if (!playerDao.existsByNickname(playerNickname)) {
                Player player = new Player();
                player.setNickname(playerNickname);
                player.setGamesTotal(row.getGamesTotal());
                player.setRolesHistoryStatistics(row);

                playerDao.save(player);
                savedPlayers.add(player);
            } else if (playerDao.findByNickname(playerNickname).getRolesHistoryStatistics() == null) {
                Player player = playerDao.findByNickname(playerNickname);
                player.setRolesHistoryStatistics(row);

                playerDao.save(player);
            }
        });
        return savedPlayers;
    }

    private List<Player> savePlayersFromVisitingStatistics(List<Statistics> visitingStatistics) {
        List<Player> savedPlayers = new ArrayList<>();

        visitingStatistics.forEach(statisticsRow -> {
            VisitingStatistics row = (VisitingStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            if (!playerDao.existsByNickname(playerNickname)) {
                Player player = new Player();
                player.setNickname(playerNickname);
                player.setVisitingStatistics(row);

                playerDao.save(player);
                savedPlayers.add(player);
            } else if (playerDao.findByNickname(playerNickname).getVisitingStatistics() == null) {
                Player player = playerDao.findByNickname(playerNickname);
                player.setVisitingStatistics(row);

                playerDao.save(player);
            }
        });
        return savedPlayers;
    }

    private List<Player> savePlayersFromSerialityStatistics(List<Statistics> serialityStatistics) {
        List<Player> savedPlayers = new ArrayList<>();

        serialityStatistics.forEach(statisticsRow -> {
            SerialityStatistics row = (SerialityStatistics) statisticsRow;

            String playerNickname = row.getNickname();
            if (!playerDao.existsByNickname(playerNickname)) {
                Player player = new Player();
                player.setNickname(playerNickname);
                player.setGamesTotal(row.getGamesTotal());
                player.setSerialityStatistics(row);

                playerDao.save(player);
                savedPlayers.add(player);
            } else if (playerDao.findByNickname(playerNickname).getSerialityStatistics() == null) {
                Player player = playerDao.findByNickname(playerNickname);
                player.setSerialityStatistics(row);

                playerDao.save(player);
            }
        });
        return savedPlayers;
    }
}
