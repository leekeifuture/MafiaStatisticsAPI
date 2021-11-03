package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual.ICoupleStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual.IGamesPerNumberStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual.INumbersStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual.IRatingStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual.IRolesHistoryStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual.ISerialityStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual.IVisitingStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all.ICoupleStatisticsAllDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all.IGamesPerNumberStatisticsAllDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all.INumbersStatisticsAllDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all.IRatingStatisticsAllDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all.IRolesHistoryStatisticsAllDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all.ISerialityStatisticsAllDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all.IVisitingStatisticsAllDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.GamesPerNumberStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.CoupleStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.GamesPerNumberStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.NumbersStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.PlaceAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RatingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RolesHistoryStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.SerialityStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.VisitingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.DashboardInfo;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional.TopGamesTable;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional.TopRatingTable;
import com.mafia.statistics.MafiaStatisticsAPI.exception.PlayerNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IStatisticsService;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService implements IStatisticsService {

    private final IPlayerDao playerDao;

    private final PlayerService playerService;

    private final INumbersStatisticsDao numbersStatisticsDao;
    private final ICoupleStatisticsDao coupleStatisticsDao;
    private final IRatingStatisticsDao ratingStatisticsDao;
    private final IRolesHistoryStatisticsDao rolesHistoryStatisticsDao;
    private final IVisitingStatisticsDao visitingStatisticsDao;
    private final ISerialityStatisticsDao serialityStatisticsDao;
    private final IGamesPerNumberStatisticsDao gamesPerNumberStatisticsDao;

    private final INumbersStatisticsAllDao numbersStatisticsAllDao;
    private final ICoupleStatisticsAllDao coupleStatisticsAllDao;
    private final IRatingStatisticsAllDao ratingStatisticsAllDao;
    private final IRolesHistoryStatisticsAllDao rolesHistoryStatisticsAllDao;
    private final IVisitingStatisticsAllDao visitingStatisticsAllDao;
    private final ISerialityStatisticsAllDao serialityStatisticsAllDao;
    private final IGamesPerNumberStatisticsAllDao gamesPerNumberStatisticsAllDao;

    @Override
    public DashboardInfo getDashboardInfo() throws PlayerNotFoundException {
        Long minimalExperienceGames = 10L;

        SerialityStatistics winSeries = serialityStatisticsDao
                .findFirstByOrderByMaximumSeriesOfWinDesc();
        Player winSeriesPlayer = playerService.getPlayerByNickname(winSeries.getNickname());

        SerialityStatistics defeatSeries = serialityStatisticsDao
                .findFirstByOrderByMaximumSeriesOfDefeatDesc();
        Player defeatSeriesPlayer = playerService.getPlayerByNickname(defeatSeries.getNickname());

        RolesHistoryStatistics firstShooting = rolesHistoryStatisticsDao
                .findFirstByGamesTotalGreaterThanOrderByPercentFirstShootingDesc(minimalExperienceGames);
        Player firstShootingPlayer = playerService.getPlayerByNickname(firstShooting.getNickname());

        Object[] visitingSeries = (Object[]) visitingStatisticsDao.findMostVisitedPlayer();
        Player visitingSeriesPlayer = playerService.getPlayerByNickname((String) visitingSeries[0]);
        Double visitingSeriesPercent = (Double) visitingSeries[1];

        List<TopGamesTable> topGamesTable = new ArrayList<>();
        playerDao.findTopPlayersByGamesTotal(PageRequest.of(0, 15)).forEach(row -> {
            try {
                Player player = playerService.getPlayerByNickname(row.getNickname());

                topGamesTable.add(new TopGamesTable(
                        player.getId(),
                        player.getGender(),
                        row.getNickname(),
                        row.getGamesTotal()
                ));
            } catch (PlayerNotFoundException e) {
                e.printStackTrace();
            }
        });

        List<TopRatingTable> topRatingTable = new ArrayList<>();
        ratingStatisticsDao.findTop15ByGamesTotalGreaterThanOrderByPointsDesc(minimalExperienceGames)
                .forEach(row -> {
                    try {
                        Player player = playerService.getPlayerByNickname(row.getNickname());

                        topRatingTable.add(new TopRatingTable(
                                player.getId(),
                                player.getGender(),
                                row.getNickname(),
                                row.getPoints()
                        ));
                    } catch (PlayerNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        return new DashboardInfo(
                winSeriesPlayer.getId(),
                winSeriesPlayer.getGender(),
                winSeriesPlayer.getNickname(),
                winSeries.getMaximumSeriesOfWin(),

                defeatSeriesPlayer.getId(),
                defeatSeriesPlayer.getGender(),
                defeatSeriesPlayer.getNickname(),
                defeatSeries.getMaximumSeriesOfDefeat(),

                visitingSeriesPlayer.getId(),
                visitingSeriesPlayer.getGender(),
                visitingSeriesPlayer.getNickname(),
                visitingSeriesPercent,

                firstShootingPlayer.getId(),
                firstShootingPlayer.getGender(),
                firstShootingPlayer.getNickname(),
                firstShooting.getPercentFirstShooting(),

                topGamesTable,
                topRatingTable
        );
    }

    // Save statistics

    @Override
    public void saveStatistics(List<Statistics> statistics) {
        if (statistics.isEmpty()) {
            return;
        }

        switch (statistics.get(0).getClass().getSimpleName()) {
            case "NumbersStatisticsAll":
                saveNumbersStatistics(statistics);
                break;
            case "CoupleStatisticsAll":
                saveCoupleStatistics(statistics);
                break;
            case "RatingStatisticsAll":
                saveRatingStatistics(statistics);
                break;
            case "RolesHistoryStatisticsAll":
                saveRolesHistoryStatistics(statistics);
                break;
            case "VisitingStatisticsAll":
                saveVisitingStatistics(statistics);
                break;
            case "SerialityStatisticsAll":
                saveSerialityStatistics(statistics);
                break;
            case "GamesPerNumberStatisticsAll":
                saveGamesPerNumberStatistics(statistics);
                break;
        }
    }

    private void saveNumbersStatistics(List<Statistics> numbersStatistics) {
        List<Statistics> activeNumbersStatistics =
                (List<Statistics>) (List<?>) numbersStatisticsAllDao.findAllByIsActive(true);

        List<NumbersStatisticsAll> notActualStatistics =
                getNotActualStatistics(
                        numbersStatistics,
                        activeNumbersStatistics,
                        NumbersStatisticsAll.class
                );

        // Save not actual statistics
        numbersStatisticsAllDao.saveAll(notActualStatistics);

        // Save new statistics
        numbersStatisticsAllDao
                .saveAll((List<NumbersStatisticsAll>) (List<?>) numbersStatistics);

        // Group data by nickname and aggregate it
        List<NumbersStatistics> aggregatedStatistics =
                numbersStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        numbersStatisticsDao.findAll().forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setNumbersStatistics(null);
            playerDao.save(player);

            numbersStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        numbersStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<Player> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setNumbersStatistics(statistics);

            if (player.getGamesTotal() == null) {
                player.setGamesTotal(statistics.getGamesTotal());
            }

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);
    }

    private void saveCoupleStatistics(List<Statistics> coupleStatistics) {
        List<Statistics> activeCoupleStatistics =
                (List<Statistics>) (List<?>) coupleStatisticsAllDao.findAllByIsActive(true);

        List<CoupleStatisticsAll> notActualStatistics =
                getNotActualStatistics(
                        coupleStatistics,
                        activeCoupleStatistics,
                        CoupleStatisticsAll.class
                );

        // Save not actual statistics
        coupleStatisticsAllDao.saveAll(notActualStatistics);

        // Save new statistics
        coupleStatisticsAllDao
                .saveAll((List<CoupleStatisticsAll>) (List<?>) coupleStatistics);

        // Group data by nickname and aggregate it
        List<CoupleStatistics> aggregatedStatistics =
                coupleStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        coupleStatisticsDao.findAll().forEach(statistics -> {
            // Player one
            Player playerOne = playerDao.findByNickname(statistics.getNicknameOfMafiaOne());
            playerOne.setCoupleStatistics(null);
            playerDao.save(playerOne);

            // Player two
            Player playerTwo = playerDao.findByNickname(statistics.getNicknameOfMafiaTwo());
            playerTwo.setCoupleStatistics(null);
            playerDao.save(playerTwo);

            coupleStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        coupleStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<Player> updatedPlayers = new ArrayList<>();

        Set<String> couplePlayers = new HashSet<>();
        aggregatedStatistics.forEach(statistics -> {
            couplePlayers.add(statistics.getNicknameOfMafiaOne());
            couplePlayers.add(statistics.getNicknameOfMafiaTwo());
        });

        couplePlayers.forEach(playerNickname -> {
            List<CoupleStatistics> coupleStatisticsList = new ArrayList<>();
            aggregatedStatistics.forEach(statistics -> {
                if (statistics.getNicknameOfMafiaOne().equals(playerNickname) ||
                        statistics.getNicknameOfMafiaTwo().equals(playerNickname)) {
                    coupleStatisticsList.add(statistics);
                }
            });

            Player player = playerDao.findByNickname(playerNickname);
            player.setCoupleStatistics(coupleStatisticsList);
            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);
    }

    private void saveRatingStatistics(List<Statistics> ratingStatistics) {
        List<Statistics> activeRatingStatistics =
                (List<Statistics>) (List<?>) ratingStatisticsAllDao.findAllByIsActive(true);

        List<RatingStatisticsAll> notActualStatistics =
                getNotActualStatistics(
                        ratingStatistics,
                        activeRatingStatistics,
                        RatingStatisticsAll.class
                );

        // Save not actual statistics
        ratingStatisticsAllDao.saveAll(notActualStatistics);

        // Save new statistics
        ratingStatisticsAllDao
                .saveAll((List<RatingStatisticsAll>) (List<?>) ratingStatistics);

        // Group data by nickname and aggregate it
        List<RatingStatistics> aggregatedStatistics =
                ratingStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        ratingStatisticsDao.findAll().forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setRatingStatistics(null);
            playerDao.save(player);

            ratingStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        ratingStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<Player> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setRatingStatistics(statistics);
            player.setGamesTotal(statistics.getGamesTotal());

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);
    }

    private void saveRolesHistoryStatistics(List<Statistics> rolesHistoryStatistics) {
        List<Statistics> activeRolesHistoryStatistics =
                (List<Statistics>) (List<?>) rolesHistoryStatisticsAllDao.findAllByIsActive(true);

        List<RolesHistoryStatisticsAll> notActualStatistics =
                getNotActualStatistics(
                        rolesHistoryStatistics,
                        activeRolesHistoryStatistics,
                        RolesHistoryStatisticsAll.class
                );

        // Save not actual statistics
        rolesHistoryStatisticsAllDao.saveAll(notActualStatistics);

        // Save new statistics
        rolesHistoryStatisticsAllDao
                .saveAll((List<RolesHistoryStatisticsAll>) (List<?>) rolesHistoryStatistics);

        // Group data by nickname and aggregate it
        List<RolesHistoryStatistics> aggregatedStatistics =
                rolesHistoryStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        rolesHistoryStatisticsDao.findAll().forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setRolesHistoryStatistics(null);
            playerDao.save(player);

            rolesHistoryStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        rolesHistoryStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<Player> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setRolesHistoryStatistics(statistics);

            if (player.getGamesTotal() == null) {
                player.setGamesTotal(statistics.getGamesTotal());
            }

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);
    }

    private void saveVisitingStatistics(List<Statistics> visitingStatistics) {
        List<Statistics> activeVisitingStatistics =
                (List<Statistics>) (List<?>) visitingStatisticsAllDao.findAllByIsActive(true);

        List<VisitingStatisticsAll> notActualStatistics =
                getNotActualStatistics(
                        visitingStatistics,
                        activeVisitingStatistics,
                        VisitingStatisticsAll.class
                );

        // Save not actual statistics
        visitingStatisticsAllDao.saveAll(notActualStatistics);

        // Save new statistics
        visitingStatisticsAllDao
                .saveAll((List<VisitingStatisticsAll>) (List<?>) visitingStatistics);

        // Group data by nickname and aggregate it
        List<VisitingStatistics> aggregatedStatistics =
                visitingStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        visitingStatisticsDao.findAll().forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setVisitingStatistics(null);
            playerDao.save(player);

            visitingStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        visitingStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<Player> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setVisitingStatistics(statistics);

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);
    }

    private void saveSerialityStatistics(List<Statistics> serialityStatistics) {
        List<Statistics> activeSerialityStatistics =
                (List<Statistics>) (List<?>) serialityStatisticsAllDao.findAllByIsActive(true);

        List<SerialityStatisticsAll> notActualStatistics =
                getNotActualStatistics(
                        serialityStatistics,
                        activeSerialityStatistics,
                        SerialityStatisticsAll.class
                );

        // Save not actual statistics
        serialityStatisticsAllDao.saveAll(notActualStatistics);

        // Save new statistics
        serialityStatisticsAllDao
                .saveAll((List<SerialityStatisticsAll>) (List<?>) serialityStatistics);

        // Group data by nickname and aggregate it
        List<SerialityStatistics> aggregatedStatistics =
                serialityStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        serialityStatisticsDao.findAll().forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setSerialityStatistics(null);
            playerDao.save(player);

            serialityStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        serialityStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<Player> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            Player player = playerDao.findByNickname(statistics.getNickname());
            player.setSerialityStatistics(statistics);

            if (player.getGamesTotal() == null) {
                player.setGamesTotal(statistics.getGamesTotal());
            }

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);
    }

    private void saveGamesPerNumberStatistics(List<Statistics> gamesPerNumberStatistics) {
        List<Statistics> activeGamesPerNumberStatistics =
                (List<Statistics>) (List<?>) gamesPerNumberStatisticsAllDao.findAllByIsActive(true);

        List<GamesPerNumberStatisticsAll> notActualStatistics =
                getNotActualStatistics(
                        gamesPerNumberStatistics,
                        activeGamesPerNumberStatistics,
                        GamesPerNumberStatisticsAll.class
                );

        // Save not actual statistics
        gamesPerNumberStatisticsAllDao.saveAll(notActualStatistics);

        // Save new statistics
        gamesPerNumberStatisticsAllDao
                .saveAll((List<GamesPerNumberStatisticsAll>) (List<?>) gamesPerNumberStatistics);

        // Group data by nickname and aggregate it
        List<GamesPerNumberStatistics> aggregatedStatistics =
                gamesPerNumberStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        gamesPerNumberStatisticsDao.deleteAll();

        // Update actual statistics
        gamesPerNumberStatisticsDao.saveAll(aggregatedStatistics);
    }

    private <T> List<T> getNotActualStatistics(
            List<Statistics> newStatistics,
            List<Statistics> activeStatistics,
            Class<T> statisticsTypeClass
    ) {
        // Collect all dates ranges
        Set<List<Date>> datesSet = new HashSet();
        activeStatistics.forEach(statisticsRow -> {
            List<Date> dates = new ArrayList<>();
            dates.add(statisticsRow.getFromDate());
            dates.add(statisticsRow.getToDate());

            datesSet.add(dates);
        });

        // Range of uploaded statistics
        Date statisticsFromDate = (newStatistics.get(0)).getFromDate();
        Date statisticsToDate = (newStatistics.get(0)).getToDate();

        // Filter not actual dates
        Set<List<Date>> notActualDates = datesSet.stream().filter(actualDates -> {
            Date actualDateFrom = actualDates.get(0);
            Date actualDateTo = actualDates.get(1);

            // Condition for replacing old data to new
            return !(statisticsToDate.before(actualDateFrom) ||
                    statisticsFromDate.after(actualDateTo));
        }).collect(Collectors.toSet());

        // Collect statistics for updating
        List<Statistics> statisticsForSave = new ArrayList<>();
        notActualDates.forEach(notActualDate -> {
            Date notActualDateFrom = notActualDate.get(0);
            Date notActualDateTo = notActualDate.get(1);

            activeStatistics.forEach(statisticsRow -> {
                if (statisticsRow.getFromDate().equals(notActualDateFrom) &&
                        statisticsRow.getToDate().equals(notActualDateTo)) {
                    // Deactivating statistics
                    statisticsRow.setIsActive(false);
                    statisticsForSave.add(
                            (Statistics) statisticsTypeClass.cast(statisticsRow)
                    );
                }
            });
        });
        return (List<T>) statisticsForSave;
    }

    // Parse statistics

    @Override
    public List<Statistics> parseStatistics(
            Map<Integer, List<String>> table,
            StatisticsType statisticsType
    ) {
        switch (statisticsType) {
            case NUMBERS:
                return parseNumbersStatistics(table);
            case COUPLE:
                return parseCoupleStatistics(table);
            case RATING:
                return parseRatingStatistics(table);
            case ROLES_HISTORY:
                return parseRolesHistoryStatistics(table);
            case VISITING:
                return parseVisitingStatistics(table);
            case GAMES_PER_NUMBER:
                return parseGamesPerNumberStatistics(table);
            case SERIALITY:
                return parseSerialityStatistics(table);
            default:
                return new ArrayList<>();
        }
    }

    private List<Statistics> parseNumbersStatistics(Map<Integer, List<String>> table) {
        int start = 7;
        int finish = table.size() - 1;
        List<Statistics> numbersStatistics = new ArrayList<>();
        Date currentDate = new Date();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(2)));
            }

            if (index < start || index > finish) {
                return;
            }

            PlaceAll placeOne = new PlaceAll(null,
                    parseCellInteger(row.get(3)), parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)), parseCellInteger(row.get(6)),
                    parseCellInteger(row.get(7)), parseCellInteger(row.get(8)));
            PlaceAll placeTwo = new PlaceAll(null,
                    parseCellInteger(row.get(9)), parseCellInteger(row.get(10)),
                    parseCellInteger(row.get(11)), parseCellInteger(row.get(12)),
                    parseCellInteger(row.get(13)), parseCellInteger(row.get(14)));
            PlaceAll placeThree = new PlaceAll(null,
                    parseCellInteger(row.get(15)), parseCellInteger(row.get(16)),
                    parseCellInteger(row.get(17)), parseCellInteger(row.get(18)),
                    parseCellInteger(row.get(19)), parseCellInteger(row.get(20)));
            PlaceAll placeFour = new PlaceAll(null,
                    parseCellInteger(row.get(21)), parseCellInteger(row.get(22)),
                    parseCellInteger(row.get(23)), parseCellInteger(row.get(24)),
                    parseCellInteger(row.get(25)), parseCellInteger(row.get(26)));
            PlaceAll placeFive = new PlaceAll(null,
                    parseCellInteger(row.get(27)), parseCellInteger(row.get(28)),
                    parseCellInteger(row.get(29)), parseCellInteger(row.get(30)),
                    parseCellInteger(row.get(31)), parseCellInteger(row.get(32)));
            PlaceAll placeSix = new PlaceAll(null,
                    parseCellInteger(row.get(33)), parseCellInteger(row.get(34)),
                    parseCellInteger(row.get(35)), parseCellInteger(row.get(36)),
                    parseCellInteger(row.get(37)), parseCellInteger(row.get(38)));
            PlaceAll placeSeven = new PlaceAll(null,
                    parseCellInteger(row.get(39)), parseCellInteger(row.get(40)),
                    parseCellInteger(row.get(41)), parseCellInteger(row.get(42)),
                    parseCellInteger(row.get(43)), parseCellInteger(row.get(44)));
            PlaceAll placeEight = new PlaceAll(null,
                    parseCellInteger(row.get(45)), parseCellInteger(row.get(46)),
                    parseCellInteger(row.get(47)), parseCellInteger(row.get(48)),
                    parseCellInteger(row.get(49)), parseCellInteger(row.get(50)));
            PlaceAll placeNine = new PlaceAll(null,
                    parseCellInteger(row.get(51)), parseCellInteger(row.get(52)),
                    parseCellInteger(row.get(53)), parseCellInteger(row.get(54)),
                    parseCellInteger(row.get(55)), parseCellInteger(row.get(56)));
            PlaceAll placeTen = new PlaceAll(null,
                    parseCellInteger(row.get(57)), parseCellInteger(row.get(58)),
                    parseCellInteger(row.get(59)), parseCellInteger(row.get(60)),
                    parseCellInteger(row.get(61)), parseCellInteger(row.get(62)));

            numbersStatistics.add(new NumbersStatisticsAll(
                    null, // id
                    row.get(1), // nickname
                    dates.get(0), // fromDate
                    dates.get(1), // toDate
                    parseCellInteger(row.get(2)), // gamesTotal
                    placeOne, // placeOne
                    placeTwo, // placeTwo
                    placeThree, // placeThree
                    placeFour, // placeFour
                    placeFive, // placeFive
                    placeSix, // placeSix
                    placeSeven, // placeSeven
                    placeEight, // placeEight
                    placeNine, // placeNine
                    placeTen, // placeTen
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        return numbersStatistics;
    }

    private List<Statistics> parseCoupleStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 1;
        List<Statistics> coupleStatistics = new ArrayList<>();
        Date currentDate = new Date();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(1)));
            }

            if (index < start || index > finish) {
                return;
            }

            coupleStatistics.add(new CoupleStatisticsAll(
                    null, // id
                    dates.get(0), // fromDate
                    dates.get(1), // toDate
                    row.get(2), // nicknameOfMafiaOne
                    row.get(3), // nicknameOfMafiaTwo
                    parseCellInteger(row.get(4)), // games
                    parseCellInteger(row.get(5)), // wins
                    parseCellFloat(row.get(6)), // percentOfWins
                    parseCellInteger(row.get(1)), // number
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        return coupleStatistics;
    }

    private List<Statistics> parseRatingStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 3;
        List<Statistics> ratingStatistics = new ArrayList<>();
        Date currentDate = new Date();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(2)));
            }

            if (index < start || index > finish) {
                return;
            }

            ratingStatistics.add(new RatingStatisticsAll(
                    null, // id
                    row.get(2), // nickname
                    dates.get(0), // fromDate
                    dates.get(1), // toDate
                    parseCellInteger(row.get(3)), // gamesTotal
                    parseCellInteger(row.get(4)), // gamesRed
                    parseCellInteger(row.get(5)), // gamesBlack
                    parseCellInteger(row.get(6)), // gamesDon
                    parseCellInteger(row.get(7)), // gamesSheriff
                    parseCellFloat(row.get(8)), // bestMove
                    parseCellFloat(row.get(9)), // penaltyPoints
                    parseCellFloat(row.get(10)), // points
                    parseCellInteger(row.get(1)), // number
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        return ratingStatistics;
    }

    private List<Statistics> parseRolesHistoryStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 3;
        List<Statistics> rolesHistoryStatistics = new ArrayList<>();
        Date currentDate = new Date();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(2)));
            }

            if (index < start || index > finish) {
                return;
            }

            rolesHistoryStatistics.add(new RolesHistoryStatisticsAll(
                    null, // id;
                    row.get(12), // nickname;
                    dates.get(0), // fromDate;
                    dates.get(1), // toDate;
                    parseCellInteger(row.get(13)), // gamesTotal;
                    parseCellInteger(row.get(14)), // gamesRed;
                    parseCellInteger(row.get(15)), // gamesBlack;
                    parseCellInteger(row.get(16)), // gamesDon;
                    parseCellInteger(row.get(17)), // gamesSheriff;
                    parseCellInteger(row.get(18)), // shooting;
                    parseCellInteger(row.get(20)), // percentSelectedRed;
                    parseCellInteger(row.get(21)), // percentSelectedBlack;
                    parseCellInteger(row.get(22)), // percentSelectedDon;
                    parseCellInteger(row.get(23)), // percentSelectedSheriff;
                    parseCellInteger(row.get(24)), // percentSelectedAllRed;
                    parseCellInteger(row.get(25)), // percentSelectedAllBlack;
                    parseCellInteger(row.get(26)), // percentWinningRed;
                    parseCellInteger(row.get(27)), // percentWinningBlack;
                    parseCellInteger(row.get(28)), // percentWinningDon;
                    parseCellInteger(row.get(29)), // percentWinningSheriff;
                    parseCellInteger(row.get(30)), // percentWinning;
                    parseCellInteger(row.get(31)), // percentWinningAllRed;
                    parseCellInteger(row.get(32)), // percentWinningAllBlack;
                    parseCellInteger(row.get(33)), // percentBestPlayer;
                    parseCellInteger(row.get(34)), // percentFirstShooting;
                    parseCellInteger(row.get(1)), // number;
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        return rolesHistoryStatistics;
    }

    private List<Statistics> parseVisitingStatistics(Map<Integer, List<String>> table) {
        int start = 7;
        int finish = table.size() - 1;
        List<Statistics> visitingStatistics = new ArrayList<>();
        Date currentDate = new Date();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(1)));
            }

            if (index < start || index > finish) {
                return;
            }

            visitingStatistics.add(new VisitingStatisticsAll(
                    null, // id
                    row.get(1), // nickname
                    dates.get(0), // fromDate
                    dates.get(1), // toDate
                    parseCellInteger(row.get(2)), // byMonday
                    parseCellInteger(row.get(3)), // byTuesday
                    parseCellInteger(row.get(4)), // byWednesday
                    parseCellInteger(row.get(5)), // byThursday
                    parseCellInteger(row.get(6)), // byFriday
                    parseCellInteger(row.get(7)), // bySaturday
                    parseCellInteger(row.get(8)), // bySunday
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        return visitingStatistics;
    }

    private List<Statistics> parseGamesPerNumberStatistics(Map<Integer, List<String>> table) {
        int start = 7;
        int finish = table.size() - 1;
        List<Statistics> gamesPerNumberStatistics = new ArrayList<>();
        Date currentDate = new Date();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(2)));
            }

            if (index < start || index > finish) {
                return;
            }

            gamesPerNumberStatistics.add(new GamesPerNumberStatisticsAll(
                    null, // id
                    parseCellInteger(row.get(1)), // number
                    dates.get(0), // fromDate
                    dates.get(1), // toDate
                    parseCellInteger(row.get(2)), // gamesTotal
                    parseCellInteger(row.get(3)), // firstShot
                    parseCellInteger(row.get(4)), // percentFirstShot
                    parseCellInteger(row.get(5)), // percentSelectedRed
                    parseCellInteger(row.get(6)), // percentSelectedBlack
                    parseCellInteger(row.get(7)), // percentSelectedDon
                    parseCellInteger(row.get(8)), // percentSelectedSheriff
                    parseCellInteger(row.get(9)), // percentWinningRed
                    parseCellInteger(row.get(10)), // percentWinningBlack
                    parseCellInteger(row.get(11)), // percentWinningDon
                    parseCellInteger(row.get(12)), // percentWinningSheriff
                    parseCellInteger(row.get(13)), // percentWinningAllRed
                    parseCellInteger(row.get(14)), // percentWinningAllBlack
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        return gamesPerNumberStatistics;
    }

    private List<Statistics> parseSerialityStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 1;
        List<Statistics> serialityStatistics = new ArrayList<>();
        Date currentDate = new Date();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(1)));
            }

            if (index < start || index > finish) {
                return;
            }

            serialityStatistics.add(new SerialityStatisticsAll(
                    null, // id
                    dates.get(0), // fromDate
                    dates.get(1), // toDate
                    row.get(1), // nickname
                    parseCellInteger(row.get(2)), // gamesTotal
                    parseCellInteger(row.get(3)), // successivelyPlayedByRed
                    parseCellInteger(row.get(4)), // successivelyPlayedBySheriff
                    parseCellInteger(row.get(5)), // successivelyPlayedByBlack
                    parseCellInteger(row.get(6)), // successivelyPlayedByDon
                    parseCellInteger(row.get(7)), // successivelyWonByRed
                    parseCellInteger(row.get(8)), // successivelyWonBySheriff
                    parseCellInteger(row.get(9)), // successivelyWonByBlack
                    parseCellInteger(row.get(10)), // successivelyWonByDon
                    parseCellInteger(row.get(11)), // successivelyLostByRed
                    parseCellInteger(row.get(12)), // successivelyLostBySheriff
                    parseCellInteger(row.get(13)), // successivelyLostByBlack
                    parseCellInteger(row.get(14)), // successivelyLostByDon
                    parseCellInteger(row.get(15)), // maximumSeriesOfWin
                    parseCellInteger(row.get(16)), // maximumSeriesOfDefeat
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        return serialityStatistics;
    }

    private Integer parseCellInteger(String cell) {
        if (cell.equals("Нет")) {
            cell = "0";
        }

        return Integer.valueOf(cell);
    }

    private Float parseCellFloat(String cell) {
        if (cell.equals("Нет")) {
            cell = "0";
        }

        return Float.valueOf(cell);
    }

    private List<Date> parseDates(String datesCell) {
        String[] split1 = datesCell.split("\\d\\d.\\d\\d.\\d\\d");
        String[] split2 = datesCell.split(split1[0]);
        String[] strDates = split2[1].split(split1[1]);

        String strDate1 = strDates[0];
        String strDate2 = strDates[1];

        String pattern = "dd.MM.yy";
        List<Date> dates = new ArrayList<>();
        try {
            dates.add(new SimpleDateFormat(pattern).parse(strDate1));
            dates.add(new SimpleDateFormat(pattern).parse(strDate2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dates;
    }
}
