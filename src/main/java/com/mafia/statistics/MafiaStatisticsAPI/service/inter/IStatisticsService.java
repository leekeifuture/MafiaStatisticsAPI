package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.StatisticsType;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.List;
import java.util.Map;

public interface IStatisticsService {

    List<Statistics> getStatistics(Map<Integer, List<String>> table, StatisticsType statisticsType) throws Exception;
}
