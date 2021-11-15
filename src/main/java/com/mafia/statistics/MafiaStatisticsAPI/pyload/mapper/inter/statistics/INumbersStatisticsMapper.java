package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.NumbersStatistics;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface INumbersStatisticsMapper {

    NumbersStatistics dtoToNumbersStatistics(NumbersStatisticsDto numbersStatisticsDto);
}
