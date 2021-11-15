package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.RolesHistoryStatistics;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRolesHistoryStatisticsMapper {

    RolesHistoryStatistics dtoToRolesHistoryStatistics(RolesHistoryStatisticsDto rolesHistoryStatisticsDto);
}
