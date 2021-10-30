package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.DashboardInfo;
import com.mafia.statistics.MafiaStatisticsAPI.exception.PlayerNotFoundException;

import java.util.List;
import java.util.Map;

public interface IStatisticsService {

    DashboardInfo getDashboardInfo() throws PlayerNotFoundException;

    List<Statistics> parseStatistics(Map<Integer, List<String>> table, StatisticsType statisticsType);

    void saveStatistics(List<Statistics> statistics);
}
