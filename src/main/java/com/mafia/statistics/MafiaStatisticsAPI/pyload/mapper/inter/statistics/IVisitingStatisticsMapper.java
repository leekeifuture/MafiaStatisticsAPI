package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.VisitingStatistics;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IVisitingStatisticsMapper {

    VisitingStatistics dtoToVisitingStatistics(VisitingStatisticsDto visitingStatisticsDto);
}
