package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RatingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.DashboardInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IStatisticsService {

    DashboardInfo getDashboardInfo();

    Map<String, List<RatingStatisticsAll>> getRatingByMonths(Integer minGames);

    List<RatingStatisticsAll> getRatingByOneMonth(Integer minGames, Date date);

    List<Statistics> parseStatistics(Map<Integer, List<String>> table, StatisticsType statisticsType);

    void saveStatistics(List<Statistics> statistics);
}
