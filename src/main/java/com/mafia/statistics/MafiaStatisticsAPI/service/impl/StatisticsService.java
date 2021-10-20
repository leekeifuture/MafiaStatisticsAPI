package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
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
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatistics;
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
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IStatisticsService;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticsService implements IStatisticsService {

    private final IPlayerDao playerDao;

    private final ISerialityStatisticsDao serialityStatisticsDao;
    private final IRolesHistoryStatisticsDao rolesHistoryStatisticsDao;
    private final IVisitingStatisticsDao visitingStatisticsDao;
    private final IRatingStatisticsDao ratingStatisticsDao;

    private final INumbersStatisticsAllDao numbersStatisticsAllDao;
    private final ICoupleStatisticsAllDao coupleStatisticsAllDao;
    private final IRatingStatisticsAllDao ratingStatisticsAllDao;
    private final IRolesHistoryStatisticsAllDao rolesHistoryStatisticsAllDao;
    private final IVisitingStatisticsAllDao visitingStatisticsAllDao;
    private final ISerialityStatisticsAllDao serialityStatisticsAllDao;
    private final IGamesPerNumberStatisticsAllDao gamesPerNumberStatisticsAllDao;

    @Override
    public DashboardInfo getDashboardInfo() {
        SerialityStatistics winSeries = serialityStatisticsDao.findFirstByOrderByMaximumSeriesOfWinDesc();
        Player winSeriesPlayer = playerDao.findByNickname(winSeries.getNickname());

        SerialityStatistics defeatSeries = serialityStatisticsDao.findFirstByOrderByMaximumSeriesOfDefeatDesc();
        Player defeatSeriesPlayer = playerDao.findByNickname(defeatSeries.getNickname());

        RolesHistoryStatistics firstShooting = rolesHistoryStatisticsDao.findFirstByOrderByPercentFirstShootingDesc();
        Player firstShootingPlayer = playerDao.findByNickname(firstShooting.getNickname());

        Object[] visitingSeries = (Object[]) visitingStatisticsDao.findMostVisitedPlayer();
        Player visitingSeriesPlayer = playerDao.findByNickname((String) visitingSeries[0]);

        List<TopGamesTable> topGamesTable = new ArrayList<>();
        playerDao.findTop15ByOrderByGamesTotalDesc().forEach(row -> {
            topGamesTable.add(new TopGamesTable(
                    row.getId(),
                    row.getGender(),
                    row.getNickname(),
                    row.getGamesTotal()
            ));
        });

        List<TopRatingTable> topRatingTable = new ArrayList<>();
        ratingStatisticsDao.findTop15ByGamesTotalGreaterThanOrderByPointsDesc(10).forEach(row -> {
            Player player = playerDao.findByNickname(row.getNickname());
            topRatingTable.add(new TopRatingTable(
                    player.getId(),
                    player.getGender(),
                    row.getNickname(),
                    row.getPoints()
            ));
        });

        return new DashboardInfo(
                winSeriesPlayer.getId(),
                winSeriesPlayer.getNickname(),
                winSeries.getMaximumSeriesOfWin(),

                defeatSeriesPlayer.getId(),
                defeatSeriesPlayer.getNickname(),
                defeatSeries.getMaximumSeriesOfDefeat(),

                visitingSeriesPlayer.getId(),
                visitingSeriesPlayer.getNickname(),
                (Integer) visitingSeries[1],

                firstShootingPlayer.getId(),
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
        numbersStatistics.forEach(statisticsRow -> {
            NumbersStatisticsAll numbersStatisticsAll =
                    ((NumbersStatisticsAll) statisticsRow);
            numbersStatisticsAll.setUploadingDate(new Date());

            numbersStatisticsAllDao.save(numbersStatisticsAll);
        });
    }

    private void saveCoupleStatistics(List<Statistics> coupleStatistics) {
        coupleStatistics.forEach(statisticsRow -> {
            CoupleStatisticsAll coupleStatisticsAll =
                    ((CoupleStatisticsAll) statisticsRow);
            coupleStatisticsAll.setUploadingDate(new Date());

            coupleStatisticsAllDao.save(coupleStatisticsAll);
        });
    }

    private void saveRatingStatistics(List<Statistics> ratingStatistics) {
        ratingStatistics.forEach(statisticsRow -> {
            RatingStatisticsAll ratingStatisticsAll =
                    ((RatingStatisticsAll) statisticsRow);
            ratingStatisticsAll.setUploadingDate(new Date());

            ratingStatisticsAllDao.save(ratingStatisticsAll);
        });
    }

    private void saveRolesHistoryStatistics(List<Statistics> rolesHistoryStatistics) {
        rolesHistoryStatistics.forEach(statisticsRow -> {
            RolesHistoryStatisticsAll rolesHistoryStatisticsAll =
                    ((RolesHistoryStatisticsAll) statisticsRow);
            rolesHistoryStatisticsAll.setUploadingDate(new Date());

            rolesHistoryStatisticsAllDao.save(rolesHistoryStatisticsAll);
        });
    }

    private void saveVisitingStatistics(List<Statistics> visitingStatistics) {
        visitingStatistics.forEach(statisticsRow -> {
            VisitingStatisticsAll visitingStatisticsAll =
                    ((VisitingStatisticsAll) statisticsRow);
            visitingStatisticsAll.setUploadingDate(new Date());

            visitingStatisticsAllDao.save(visitingStatisticsAll);
        });
    }

    private void saveSerialityStatistics(List<Statistics> serialityStatistics) {
        serialityStatistics.forEach(statisticsRow -> {
            SerialityStatisticsAll serialityStatisticsAll =
                    ((SerialityStatisticsAll) statisticsRow);
            serialityStatisticsAll.setUploadingDate(new Date());

            serialityStatisticsAllDao.save(serialityStatisticsAll);
        });
    }

    private void saveGamesPerNumberStatistics(List<Statistics> gamesPerNumberStatistics) {
        gamesPerNumberStatistics.forEach(statisticsRow -> {
            GamesPerNumberStatisticsAll gamesPerNumberStatisticsAll =
                    ((GamesPerNumberStatisticsAll) statisticsRow);
            gamesPerNumberStatisticsAll.setUploadingDate(new Date());

            gamesPerNumberStatisticsAllDao.save(gamesPerNumberStatisticsAll);
        });
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
                    null,
                    dates.get(0),
                    dates.get(1),
                    row.get(1),
                    parseCellInteger(row.get(2)),
                    placeOne,
                    placeTwo,
                    placeThree,
                    placeFour,
                    placeFive,
                    placeSix,
                    placeSeven,
                    placeEight,
                    placeNine,
                    placeTen,
                    null,
                    new Date()
            ));
        });

        return numbersStatistics;
    }

    private List<Statistics> parseCoupleStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 1;
        List<Statistics> coupleStatistics = new ArrayList<>();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(1)));
            }

            if (index < start || index > finish) {
                return;
            }

            coupleStatistics.add(new CoupleStatisticsAll(
                    null,
                    dates.get(0),
                    dates.get(1),
                    parseCellInteger(row.get(1)),
                    row.get(2),
                    row.get(3),
                    parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)),
                    parseCellFloat(row.get(6)),
                    null,
                    new Date()
            ));
        });

        return coupleStatistics;
    }

    private List<Statistics> parseRatingStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 3;
        List<Statistics> ratingStatistics = new ArrayList<>();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(2)));
            }

            if (index < start || index > finish) {
                return;
            }

            ratingStatistics.add(new RatingStatisticsAll(
                    null,
                    dates.get(0),
                    dates.get(1),
                    parseCellInteger(row.get(1)),
                    row.get(2),
                    parseCellInteger(row.get(3)),
                    parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)),
                    parseCellInteger(row.get(6)),
                    parseCellInteger(row.get(7)),
                    parseCellFloat(row.get(8)),
                    parseCellFloat(row.get(9)),
                    parseCellFloat(row.get(10)),
                    null,
                    new Date()
            ));
        });

        return ratingStatistics;
    }

    private List<Statistics> parseRolesHistoryStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 3;
        List<Statistics> rolesHistoryStatistics = new ArrayList<>();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(2)));
            }

            if (index < start || index > finish) {
                return;
            }

            rolesHistoryStatistics.add(new RolesHistoryStatisticsAll(
                    null,
                    dates.get(0),
                    dates.get(1),
                    parseCellInteger(row.get(1)),
                    row.get(12),
                    parseCellInteger(row.get(13)),
                    parseCellInteger(row.get(14)),
                    parseCellInteger(row.get(15)),
                    parseCellInteger(row.get(16)),
                    parseCellInteger(row.get(17)),
                    parseCellInteger(row.get(18)),
                    parseCellInteger(row.get(20)),
                    parseCellInteger(row.get(21)),
                    parseCellInteger(row.get(22)),
                    parseCellInteger(row.get(23)),
                    parseCellInteger(row.get(24)),
                    parseCellInteger(row.get(25)),
                    parseCellInteger(row.get(26)),
                    parseCellInteger(row.get(27)),
                    parseCellInteger(row.get(28)),
                    parseCellInteger(row.get(29)),
                    parseCellInteger(row.get(30)),
                    parseCellInteger(row.get(31)),
                    parseCellInteger(row.get(32)),
                    parseCellInteger(row.get(33)),
                    parseCellInteger(row.get(34)),
                    null,
                    new Date()
            ));
        });

        return rolesHistoryStatistics;
    }

    private List<Statistics> parseVisitingStatistics(Map<Integer, List<String>> table) {
        int start = 7;
        int finish = table.size() - 1;
        List<Statistics> visitingStatistics = new ArrayList<>();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(1)));
            }

            if (index < start || index > finish) {
                return;
            }

            visitingStatistics.add(new VisitingStatisticsAll(
                    null,
                    dates.get(0),
                    dates.get(1),
                    row.get(1),
                    parseCellInteger(row.get(2)),
                    parseCellInteger(row.get(3)),
                    parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)),
                    parseCellInteger(row.get(6)),
                    parseCellInteger(row.get(7)),
                    parseCellInteger(row.get(8)),
                    null,
                    new Date()
            ));
        });

        return visitingStatistics;
    }

    private List<Statistics> parseGamesPerNumberStatistics(Map<Integer, List<String>> table) {
        int start = 7;
        int finish = table.size() - 1;
        List<Statistics> gamesPerNumberStatistics = new ArrayList<>();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(2)));
            }

            if (index < start || index > finish) {
                return;
            }

            gamesPerNumberStatistics.add(new GamesPerNumberStatisticsAll(
                    null,
                    dates.get(0),
                    dates.get(1),
                    parseCellInteger(row.get(1)),
                    parseCellInteger(row.get(2)),
                    parseCellInteger(row.get(3)),
                    parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)),
                    parseCellInteger(row.get(6)),
                    parseCellInteger(row.get(7)),
                    parseCellInteger(row.get(8)),
                    parseCellInteger(row.get(9)),
                    parseCellInteger(row.get(10)),
                    parseCellInteger(row.get(11)),
                    parseCellInteger(row.get(12)),
                    parseCellInteger(row.get(13)),
                    parseCellInteger(row.get(14)),
                    null,
                    new Date()
            ));
        });

        return gamesPerNumberStatistics;
    }

    private List<Statistics> parseSerialityStatistics(Map<Integer, List<String>> table) {
        int start = 6;
        int finish = table.size() - 1;
        List<Statistics> serialityStatistics = new ArrayList<>();

        List<Date> dates = new ArrayList<>();
        table.forEach((index, row) -> {
            if (index == 3) {
                dates.addAll(parseDates(row.get(1)));
            }

            if (index < start || index > finish) {
                return;
            }

            serialityStatistics.add(new SerialityStatisticsAll(
                    null,
                    dates.get(0),
                    dates.get(1),
                    row.get(1),
                    parseCellInteger(row.get(2)),
                    parseCellInteger(row.get(3)),
                    parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)),
                    parseCellInteger(row.get(6)),
                    parseCellInteger(row.get(7)),
                    parseCellInteger(row.get(8)),
                    parseCellInteger(row.get(9)),
                    parseCellInteger(row.get(10)),
                    parseCellInteger(row.get(11)),
                    parseCellInteger(row.get(12)),
                    parseCellInteger(row.get(13)),
                    parseCellInteger(row.get(14)),
                    parseCellInteger(row.get(15)),
                    parseCellInteger(row.get(16)),
                    null,
                    new Date()
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
