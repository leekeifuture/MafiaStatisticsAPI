package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.SerialityStatistics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISerialityStatisticsMapper {

    SerialityStatistics dtoToSerialityStatistics(SerialityStatisticsDto serialityStatisticsDto);
}
