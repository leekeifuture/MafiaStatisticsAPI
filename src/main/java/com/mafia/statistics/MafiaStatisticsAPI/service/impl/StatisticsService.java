package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.IRatingStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.IRolesHistoryStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.ISerialityStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.IVisitingStatisticsDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.Place;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.CoupleStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.GamesPerNumberStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RatingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.SerialityStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.VisitingStatistics;
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

    @Override
    public List<Statistics> getStatistics(
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
        ratingStatisticsDao.findTop15ByOrderByPointsDesc().forEach(row -> {
            Player player = playerDao.findByNickname(row.getNickname());
            topRatingTable.add(new TopRatingTable(
                    player.getId(),
                    player.getGender(),
                    row.getNickname(),
                    row.getPoints()
            ));
        });

        DashboardInfo dashboardInfo = new DashboardInfo(
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

        return dashboardInfo;
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

            Place placeOne = new Place(null,
                    parseCellInteger(row.get(3)), parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)), parseCellInteger(row.get(6)),
                    parseCellInteger(row.get(7)), parseCellInteger(row.get(8)));
            Place placeTwo = new Place(null,
                    parseCellInteger(row.get(9)), parseCellInteger(row.get(10)),
                    parseCellInteger(row.get(11)), parseCellInteger(row.get(12)),
                    parseCellInteger(row.get(13)), parseCellInteger(row.get(14)));
            Place placeThree = new Place(null,
                    parseCellInteger(row.get(15)), parseCellInteger(row.get(16)),
                    parseCellInteger(row.get(17)), parseCellInteger(row.get(18)),
                    parseCellInteger(row.get(19)), parseCellInteger(row.get(20)));
            Place placeFour = new Place(null,
                    parseCellInteger(row.get(21)), parseCellInteger(row.get(22)),
                    parseCellInteger(row.get(23)), parseCellInteger(row.get(24)),
                    parseCellInteger(row.get(25)), parseCellInteger(row.get(26)));
            Place placeFive = new Place(null,
                    parseCellInteger(row.get(27)), parseCellInteger(row.get(28)),
                    parseCellInteger(row.get(29)), parseCellInteger(row.get(30)),
                    parseCellInteger(row.get(31)), parseCellInteger(row.get(32)));
            Place placeSix = new Place(null,
                    parseCellInteger(row.get(33)), parseCellInteger(row.get(34)),
                    parseCellInteger(row.get(35)), parseCellInteger(row.get(36)),
                    parseCellInteger(row.get(37)), parseCellInteger(row.get(38)));
            Place placeSeven = new Place(null,
                    parseCellInteger(row.get(39)), parseCellInteger(row.get(40)),
                    parseCellInteger(row.get(41)), parseCellInteger(row.get(42)),
                    parseCellInteger(row.get(43)), parseCellInteger(row.get(44)));
            Place placeEight = new Place(null,
                    parseCellInteger(row.get(45)), parseCellInteger(row.get(46)),
                    parseCellInteger(row.get(47)), parseCellInteger(row.get(48)),
                    parseCellInteger(row.get(49)), parseCellInteger(row.get(50)));
            Place placeNine = new Place(null,
                    parseCellInteger(row.get(51)), parseCellInteger(row.get(52)),
                    parseCellInteger(row.get(53)), parseCellInteger(row.get(54)),
                    parseCellInteger(row.get(55)), parseCellInteger(row.get(56)));
            Place placeTen = new Place(null,
                    parseCellInteger(row.get(57)), parseCellInteger(row.get(58)),
                    parseCellInteger(row.get(59)), parseCellInteger(row.get(60)),
                    parseCellInteger(row.get(61)), parseCellInteger(row.get(62)));

            numbersStatistics.add(new NumbersStatistics(
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
                    placeTen
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

            coupleStatistics.add(new CoupleStatistics(
                    null,
                    dates.get(0),
                    dates.get(1),
                    parseCellInteger(row.get(1)),
                    row.get(2),
                    row.get(3),
                    parseCellInteger(row.get(4)),
                    parseCellInteger(row.get(5)),
                    parseCellFloat(row.get(6))
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

            ratingStatistics.add(new RatingStatistics(
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
                    parseCellFloat(row.get(10))
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

            rolesHistoryStatistics.add(new RolesHistoryStatistics(
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
                    parseCellInteger(row.get(34))
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

            visitingStatistics.add(new VisitingStatistics(
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
                    parseCellInteger(row.get(8))
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

            gamesPerNumberStatistics.add(new GamesPerNumberStatistics(
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
                    parseCellInteger(row.get(14))
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

            serialityStatistics.add(new SerialityStatistics(
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
                    parseCellInteger(row.get(16))
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
