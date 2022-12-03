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
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.GamesPerNumberStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatisticsDto;
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
import com.mafia.statistics.MafiaStatisticsAPI.exception.InternalServerException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IStatisticsService;
import com.mafia.statistics.MafiaStatisticsAPI.util.DateUtil;
import com.mafia.statistics.MafiaStatisticsAPI.util.DigitsUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService implements IStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

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
    public DashboardInfo getDashboardInfo() {
        Long minimalExperienceGames = 200L;

        SerialityStatisticsDto winSeries = serialityStatisticsDao
                .findFirstByOrderByMaximumSeriesOfWinDesc();
        PlayerDto winSeriesPlayer = playerService
                .getPlayerByNickname(winSeries.getNickname());

        SerialityStatisticsDto defeatSeries = serialityStatisticsDao
                .findFirstByOrderByMaximumSeriesOfDefeatDesc();
        PlayerDto defeatSeriesPlayer = playerService
                .getPlayerByNickname(defeatSeries.getNickname());

        RolesHistoryStatisticsDto firstShooting = rolesHistoryStatisticsDao
                .findFirstByGamesTotalGreaterThanOrderByPercentFirstShootingDesc(minimalExperienceGames);
        PlayerDto firstShootingPlayer = playerService
                .getPlayerByNickname(firstShooting.getNickname());

        Object[] visitingSeries = (Object[]) visitingStatisticsDao.findMostVisitedPlayer();
        PlayerDto visitingSeriesPlayer = playerService
                .getPlayerByNickname((String) visitingSeries[0]);
        Double visitingSeriesPercent = (Double) visitingSeries[1];

        // Successively played

        SerialityStatisticsDto playedByRedSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyPlayedByRedDesc();
        PlayerDto playedByRedSeriesPlayer = playerService
                .getPlayerByNickname("Домино"); // TODO: tmp hardcode

        SerialityStatisticsDto playedBySheriffSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyPlayedBySheriffDesc();
        PlayerDto playedBySheriffSeriesPlayer = playerService
                .getPlayerByNickname(playedBySheriffSeries.getNickname());

        SerialityStatisticsDto playedByBlackSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyPlayedByBlackDesc();
        PlayerDto playedByBlackSeriesPlayer = playerService
                .getPlayerByNickname(playedByBlackSeries.getNickname());

        SerialityStatisticsDto playedByDonSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyPlayedByDonDesc();
        PlayerDto playedByDonSeriesPlayer = playerService
                .getPlayerByNickname(playedByDonSeries.getNickname());

        // Successively won

        SerialityStatisticsDto wonByRedSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyWonByRedDesc();
        PlayerDto wonByRedSeriesPlayer = playerService
                .getPlayerByNickname(wonByRedSeries.getNickname());

        SerialityStatisticsDto wonBySheriffSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyWonBySheriffDesc();
        PlayerDto wonBySheriffSeriesPlayer = playerService
                .getPlayerByNickname(wonBySheriffSeries.getNickname());

        SerialityStatisticsDto wonByBlackSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyWonByBlackDesc();
        PlayerDto wonByBlackSeriesPlayer = playerService
                .getPlayerByNickname(wonByBlackSeries.getNickname());

        SerialityStatisticsDto wonByDonSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyWonByDonDesc();
        PlayerDto wonByDonSeriesPlayer = playerService
                .getPlayerByNickname(wonByDonSeries.getNickname());

        // Successively lost

        SerialityStatisticsDto lostByRedSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyLostByRedDesc();
        PlayerDto lostByRedSeriesPlayer = playerService
                .getPlayerByNickname(lostByRedSeries.getNickname());

        SerialityStatisticsDto lostBySheriffSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyLostBySheriffDesc();
        PlayerDto lostBySheriffSeriesPlayer = playerService
                .getPlayerByNickname(lostBySheriffSeries.getNickname());

        SerialityStatisticsDto lostByBlackSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyLostByBlackDesc();
        PlayerDto lostByBlackSeriesPlayer = playerService
                .getPlayerByNickname(lostByBlackSeries.getNickname());

        SerialityStatisticsDto lostByDonSeries = serialityStatisticsDao
                .findFirstByOrderBySuccessivelyLostByDonDesc();
        PlayerDto lostByDonSeriesPlayer = playerService
                .getPlayerByNickname(lostByDonSeries.getNickname());


        List<TopGamesTable> topGamesTable = new ArrayList<>();
        playerDao.findTopPlayersByGamesTotal(PageRequest.of(0, 15)).forEach(row -> {
            PlayerDto player = playerService.getPlayerByNickname(row.getNickname());

            topGamesTable.add(new TopGamesTable(
                    player.getId(),
                    player.getGender(),
                    player.getCustomNickname(),
                    player.getGamesTotal()
            ));
        });

        List<TopRatingTable> topRatingTable = new ArrayList<>();
        ratingStatisticsDao.findTop15ByGamesTotalGreaterThanOrderByPointsDesc(minimalExperienceGames)
                .forEach(row -> {
                    PlayerDto player = playerService.getPlayerByNickname(row.getNickname());

                    topRatingTable.add(new TopRatingTable(
                            player.getId(),
                            player.getGender(),
                            player.getCustomNickname(),
                            DigitsUtil.roundDouble(row.getPoints(), 1)
                    ));
                });

        return new DashboardInfo(
                winSeriesPlayer.getId(),
                winSeriesPlayer.getGender(),
                winSeriesPlayer.getCustomNickname(),
                winSeries.getMaximumSeriesOfWin(),

                defeatSeriesPlayer.getId(),
                defeatSeriesPlayer.getGender(),
                defeatSeriesPlayer.getCustomNickname(),
                defeatSeries.getMaximumSeriesOfDefeat(),

                visitingSeriesPlayer.getId(),
                visitingSeriesPlayer.getGender(),
                visitingSeriesPlayer.getCustomNickname(),
                DigitsUtil.roundDouble(visitingSeriesPercent, 1),

                firstShootingPlayer.getId(),
                firstShootingPlayer.getGender(),
                firstShootingPlayer.getCustomNickname(),
                DigitsUtil.roundDouble(firstShooting.getPercentFirstShooting(), 1),

                // Successively played

                playedByRedSeriesPlayer.getId(),
                playedByRedSeriesPlayer.getGender(),
                playedByRedSeriesPlayer.getCustomNickname(),
                26, // TODO: tmp hardcode

                playedBySheriffSeriesPlayer.getId(),
                playedBySheriffSeriesPlayer.getGender(),
                playedBySheriffSeriesPlayer.getCustomNickname(),
                playedBySheriffSeries.getSuccessivelyPlayedBySheriff(),

                playedByBlackSeriesPlayer.getId(),
                playedByBlackSeriesPlayer.getGender(),
                playedByBlackSeriesPlayer.getCustomNickname(),
                playedByBlackSeries.getSuccessivelyPlayedByBlack(),

                playedByDonSeriesPlayer.getId(),
                playedByDonSeriesPlayer.getGender(),
                playedByDonSeriesPlayer.getCustomNickname(),
                playedByDonSeries.getSuccessivelyPlayedByDon(),

                // Successively won

                wonByRedSeriesPlayer.getId(),
                wonByRedSeriesPlayer.getGender(),
                wonByRedSeriesPlayer.getCustomNickname(),
                wonByRedSeries.getSuccessivelyWonByRed(),

                wonBySheriffSeriesPlayer.getId(),
                wonBySheriffSeriesPlayer.getGender(),
                wonBySheriffSeriesPlayer.getCustomNickname(),
                wonBySheriffSeries.getSuccessivelyWonBySheriff(),

                wonByBlackSeriesPlayer.getId(),
                wonByBlackSeriesPlayer.getGender(),
                wonByBlackSeriesPlayer.getCustomNickname(),
                wonByBlackSeries.getSuccessivelyWonByBlack(),

                wonByDonSeriesPlayer.getId(),
                wonByDonSeriesPlayer.getGender(),
                wonByDonSeriesPlayer.getCustomNickname(),
                wonByDonSeries.getSuccessivelyWonByDon(),

                // Successively lost

                lostByRedSeriesPlayer.getId(),
                lostByRedSeriesPlayer.getGender(),
                lostByRedSeriesPlayer.getCustomNickname(),
                lostByRedSeries.getSuccessivelyLostByRed(),

                lostBySheriffSeriesPlayer.getId(),
                lostBySheriffSeriesPlayer.getGender(),
                lostBySheriffSeriesPlayer.getCustomNickname(),
                lostBySheriffSeries.getSuccessivelyLostBySheriff(),

                lostByBlackSeriesPlayer.getId(),
                lostByBlackSeriesPlayer.getGender(),
                lostByBlackSeriesPlayer.getCustomNickname(),
                lostByBlackSeries.getSuccessivelyLostByBlack(),

                lostByDonSeriesPlayer.getId(),
                lostByDonSeriesPlayer.getGender(),
                lostByDonSeriesPlayer.getCustomNickname(),
                lostByDonSeries.getSuccessivelyLostByDon(),

                topGamesTable,
                topRatingTable
        );
    }

    @Override
    public Map<String, List<RatingStatisticsAll>> getRatingByMonths(Integer minGames) {
        List<RatingStatisticsAll> ratingStatistics =
                ratingStatisticsAllDao
                        .findAllByIsActiveAndGamesTotalGreaterThanEqualOrderByPointsDesc(
                                true, minGames
                        );

        Map<String, List<RatingStatisticsAll>> ratingByMonths = new HashMap<>();

        ratingStatistics.forEach(rating -> {
            LocalDate localDate = DateUtil.convertToLocalDate(rating.getFromDate());
            String date = localDate.getYear() + "-" +
                    String.format("%02d", localDate.getMonthValue());

            if (ratingByMonths.containsKey(date)) {
                ratingByMonths.get(date).add(rating);
            } else {
                ratingByMonths.put(date, new ArrayList<>(List.of(rating)));
            }
        });

        return ratingByMonths
                .entrySet()
                .stream()
                .sorted((dateOne, dateTwo) ->
                        dateTwo.getKey().compareTo(dateOne.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new
                ));
    }

    @Override
    public List<RatingStatisticsAll> getRatingByOneMonth(Integer minGames, Date date) {
        return ratingStatisticsAllDao
                .findAllByIsActiveAndGamesTotalGreaterThanEqualAndFromDateOrderByPointsDesc(
                        true, minGames, date
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

    @Override
    public StatisticsType getStatisticsTypeOfFile(
            Map<Integer, List<String>> table
    ) {
        String table_header = table.get(3).get(2).isEmpty()
                ? table.get(3).get(1)
                : table.get(3).get(2);

        if (table.get(5).get(3).contains("отстрел")) {
            return StatisticsType.GAMES_PER_NUMBER;
        } else if (table_header.contains("номерам")) {
            return StatisticsType.NUMBERS;
        } else if (table_header.contains("мафам")) {
            return StatisticsType.COUPLE;
        } else if (table_header.contains("Рейтинговая")) {
            return StatisticsType.RATING;
        } else if (table_header.contains("посещениям")) {
            return StatisticsType.VISITING;
        } else if (table_header.contains("серийности")) {
            return StatisticsType.SERIALITY;
        } else {
            return StatisticsType.UNKNOWN;
        }
    }

    private void saveNumbersStatistics(List<Statistics> numbersStatistics) {
        logger.info("Saving numbers statistics...");

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
        List<NumbersStatisticsDto> aggregatedStatistics =
                numbersStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        numbersStatisticsDao.findAll().forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setNumbersStatistics(null);
            playerDao.save(player);

            numbersStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        numbersStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<PlayerDto> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setNumbersStatistics(statistics);

            if (player.getGamesTotal() == null || player.getGamesTotal() == 0) {
                player.setGamesTotal(statistics.getGamesTotal());
            }

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);

        logger.info("Saved numbers statistics.");
    }

    private void saveCoupleStatistics(List<Statistics> coupleStatistics) {
        logger.info("Saving couple statistics...");

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
        List<CoupleStatisticsDto> aggregatedStatistics =
                coupleStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        coupleStatisticsDao.findAll().forEach(statistics -> {
            // Player one
            PlayerDto playerOne = playerDao.findByNickname(statistics.getNicknameOfMafiaOne());
            playerOne.setCoupleStatistics(null);
            playerDao.save(playerOne);

            // Player two
            PlayerDto playerTwo = playerDao.findByNickname(statistics.getNicknameOfMafiaTwo());
            playerTwo.setCoupleStatistics(null);
            playerDao.save(playerTwo);

            coupleStatisticsDao.delete(statistics);
        });

        // Calculate percent of wins
        List<CoupleStatisticsDto> actualStatistics = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            Long percentOfWins = 100 * statistics.getWins() / statistics.getGames();
            statistics.setCalculatedPercentOfWins(percentOfWins);
            actualStatistics.add(statistics);
        });

        // Update actual statistics
        coupleStatisticsDao.saveAll(actualStatistics);

        // Update actual statistics for players
        List<PlayerDto> updatedPlayers = new ArrayList<>();

        Set<String> couplePlayers = new HashSet<>();
        aggregatedStatistics.forEach(statistics -> {
            couplePlayers.add(statistics.getNicknameOfMafiaOne());
            couplePlayers.add(statistics.getNicknameOfMafiaTwo());
        });

        couplePlayers.forEach(playerNickname -> {
            AtomicReference<Integer> counter = new AtomicReference<>(0);
            List<CoupleStatisticsDto> coupleStatisticsList = new ArrayList<>();

            aggregatedStatistics.forEach(statistics -> {
                if ((statistics.getNicknameOfMafiaOne().equals(playerNickname) ||
                        statistics.getNicknameOfMafiaTwo().equals(playerNickname)) &&
                        statistics.getWins() > 1 &&
                        counter.get() <= 3) {
                    coupleStatisticsList.add(statistics);
                    counter.updateAndGet(v -> v + 1);
                }
            });

            PlayerDto player = playerDao.findByNickname(playerNickname);
            player.setCoupleStatistics(coupleStatisticsList);
            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);

        logger.info("Saved couple statistics.");
    }

    private void saveRatingStatistics(List<Statistics> ratingStatistics) {
        logger.info("Saving rating statistics...");

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
        List<RatingStatisticsDto> preAggregatedStatistics =
                ratingStatisticsAllDao.getAggregatedData();

        // Calculating rating points
        List<RatingStatisticsDto> aggregatedStatistics = new ArrayList<>();

        preAggregatedStatistics.forEach(player -> {
            Double points = ((player.getGamesRed() +
                    player.getGamesBlack() +
                    player.getGamesDon() +
                    player.getGamesSheriff() +
                    player.getBestMove() +
                    player.getAdditionalPoints()
            ) / player.getGamesTotal()) * 100;

            player.setPoints(points);
            aggregatedStatistics.add(player);
        });

        // Remove all actual statistics for updating with new one
        ratingStatisticsDao.findAll().forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setRatingStatistics(null);
            playerDao.save(player);

            ratingStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        ratingStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<PlayerDto> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setRatingStatistics(statistics);
            player.setGamesTotal(statistics.getGamesTotal());

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);

        logger.info("Saved rating statistics.");
    }

    private void saveRolesHistoryStatistics(List<Statistics> rolesHistoryStatistics) {
        logger.info("Saving roles history statistics...");

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
        List<RolesHistoryStatisticsDto> aggregatedStatistics =
                rolesHistoryStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        rolesHistoryStatisticsDao.findAll().forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setRolesHistoryStatistics(null);
            playerDao.save(player);

            rolesHistoryStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        rolesHistoryStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<PlayerDto> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setRolesHistoryStatistics(statistics);

            if (player.getGamesTotal() == null || player.getGamesTotal() == 0) {
                player.setGamesTotal(statistics.getGamesTotal());
            }

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);

        logger.info("Saved roles history statistics.");
    }

    private void saveVisitingStatistics(List<Statistics> visitingStatistics) {
        logger.info("Saving visiting statistics...");

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
        List<VisitingStatisticsDto> aggregatedStatistics =
                visitingStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        visitingStatisticsDao.findAll().forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setVisitingStatistics(null);
            playerDao.save(player);

            visitingStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        visitingStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<PlayerDto> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setVisitingStatistics(statistics);

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);

        logger.info("Saved visiting statistics.");
    }

    private void saveSerialityStatistics(List<Statistics> serialityStatistics) {
        logger.info("Saving seriality statistics...");

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
        List<SerialityStatisticsDto> aggregatedStatistics =
                serialityStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        serialityStatisticsDao.findAll().forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setSerialityStatistics(null);
            playerDao.save(player);

            serialityStatisticsDao.delete(statistics);
        });

        // Update actual statistics
        serialityStatisticsDao.saveAll(aggregatedStatistics);

        // Update actual statistics for players
        List<PlayerDto> updatedPlayers = new ArrayList<>();
        aggregatedStatistics.forEach(statistics -> {
            PlayerDto player = playerDao.findByNickname(statistics.getNickname());
            player.setSerialityStatistics(statistics);

            if (player.getGamesTotal() == null || player.getGamesTotal() == 0) {
                player.setGamesTotal(statistics.getGamesTotal());
            }

            updatedPlayers.add(player);
        });

        playerDao.saveAll(updatedPlayers);

        logger.info("Saved seriality statistics.");
    }

    private void saveGamesPerNumberStatistics(List<Statistics> gamesPerNumberStatistics) {
        logger.info("Saving games per number statistics...");

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
        List<GamesPerNumberStatisticsDto> aggregatedStatistics =
                gamesPerNumberStatisticsAllDao.getAggregatedData();

        // Remove all actual statistics for updating with new one
        gamesPerNumberStatisticsDao.deleteAll();

        // Update actual statistics
        gamesPerNumberStatisticsDao.saveAll(aggregatedStatistics);

        logger.info("Saved games per number statistics.");
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
        logger.info("Parsing numbers statistics...");

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

            PlaceAll placeOne = new PlaceAll(
                    parseCellInteger(row.get(3)), parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)), parseCellInteger(row.get(6)),
                    parseCellInteger(row.get(7)), parseCellInteger(row.get(8))
            );
            PlaceAll placeTwo = new PlaceAll(
                    parseCellInteger(row.get(9)), parseCellInteger(row.get(10)),
                    parseCellInteger(row.get(11)), parseCellInteger(row.get(12)),
                    parseCellInteger(row.get(13)), parseCellInteger(row.get(14))
            );
            PlaceAll placeThree = new PlaceAll(
                    parseCellInteger(row.get(15)), parseCellInteger(row.get(16)),
                    parseCellInteger(row.get(17)), parseCellInteger(row.get(18)),
                    parseCellInteger(row.get(19)), parseCellInteger(row.get(20))
            );
            PlaceAll placeFour = new PlaceAll(
                    parseCellInteger(row.get(21)), parseCellInteger(row.get(22)),
                    parseCellInteger(row.get(23)), parseCellInteger(row.get(24)),
                    parseCellInteger(row.get(25)), parseCellInteger(row.get(26))
            );
            PlaceAll placeFive = new PlaceAll(
                    parseCellInteger(row.get(27)), parseCellInteger(row.get(28)),
                    parseCellInteger(row.get(29)), parseCellInteger(row.get(30)),
                    parseCellInteger(row.get(31)), parseCellInteger(row.get(32))
            );
            PlaceAll placeSix = new PlaceAll(
                    parseCellInteger(row.get(33)), parseCellInteger(row.get(34)),
                    parseCellInteger(row.get(35)), parseCellInteger(row.get(36)),
                    parseCellInteger(row.get(37)), parseCellInteger(row.get(38))
            );
            PlaceAll placeSeven = new PlaceAll(
                    parseCellInteger(row.get(39)), parseCellInteger(row.get(40)),
                    parseCellInteger(row.get(41)), parseCellInteger(row.get(42)),
                    parseCellInteger(row.get(43)), parseCellInteger(row.get(44))
            );
            PlaceAll placeEight = new PlaceAll(
                    parseCellInteger(row.get(45)), parseCellInteger(row.get(46)),
                    parseCellInteger(row.get(47)), parseCellInteger(row.get(48)),
                    parseCellInteger(row.get(49)), parseCellInteger(row.get(50))
            );
            PlaceAll placeNine = new PlaceAll(
                    parseCellInteger(row.get(51)), parseCellInteger(row.get(52)),
                    parseCellInteger(row.get(53)), parseCellInteger(row.get(54)),
                    parseCellInteger(row.get(55)), parseCellInteger(row.get(56))
            );
            PlaceAll placeTen = new PlaceAll(
                    parseCellInteger(row.get(57)), parseCellInteger(row.get(58)),
                    parseCellInteger(row.get(59)), parseCellInteger(row.get(60)),
                    parseCellInteger(row.get(61)), parseCellInteger(row.get(62))
            );

            numbersStatistics.add(new NumbersStatisticsAll(
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

        logger.info("Parsed numbers statistics from " +
                dates.get(0) + " to " + dates.get(1));

        return numbersStatistics;
    }

    private List<Statistics> parseCoupleStatistics(Map<Integer, List<String>> table) {
        logger.info("Parsing couple statistics...");

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

        logger.info("Parsed couple statistics from " +
                dates.get(0) + " to " + dates.get(1));

        return coupleStatistics;
    }

    private List<Statistics> parseRatingStatistics(Map<Integer, List<String>> table) {
        logger.info("Parsing rating statistics...");

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
                    row.get(2), // nickname
                    dates.get(0), // fromDate
                    dates.get(1), // toDate
                    parseCellInteger(row.get(3)), // gamesTotal
                    parseCellInteger(row.get(4)), // gamesRed
                    parseCellInteger(row.get(5)), // gamesBlack
                    parseCellInteger(row.get(6)), // gamesDon
                    parseCellInteger(row.get(7)), // gamesSheriff
                    parseCellFloat(row.get(8)), // bestMove
                    parseCellFloat(row.get(9)), // additionalPoints
                    parseCellFloat(row.get(10)), // points
                    parseCellInteger(row.get(1)), // number
                    true, // isActive
                    currentDate // uploadingDate
            ));
        });

        logger.info("Parsed rating statistics from " +
                dates.get(0) + " to " + dates.get(1));

        return ratingStatistics;
    }

    private List<Statistics> parseRolesHistoryStatistics(Map<Integer, List<String>> table) {
        logger.info("Parsing roles history statistics...");

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

        logger.info("Parsed roles history statistics from " +
                dates.get(0) + " to " + dates.get(1));

        return rolesHistoryStatistics;
    }

    private List<Statistics> parseVisitingStatistics(Map<Integer, List<String>> table) {
        logger.info("Parsing visiting statistics...");

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

        logger.info("Parsed visiting statistics from " +
                dates.get(0) + " to " + dates.get(1));

        return visitingStatistics;
    }

    private List<Statistics> parseSerialityStatistics(Map<Integer, List<String>> table) {
        logger.info("Parsing seriality statistics...");

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

        logger.info("Parsed seriality statistics from " +
                dates.get(0) + " to " + dates.get(1));

        return serialityStatistics;
    }

    private List<Statistics> parseGamesPerNumberStatistics(Map<Integer, List<String>> table) {
        logger.info("Parsing games per number statistics...");

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

        logger.info("Parsed games per number statistics from " +
                dates.get(0) + " to " + dates.get(1));

        return gamesPerNumberStatistics;
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
            throw new InternalServerException(e.getMessage());
        }

        return dates;
    }
}
