package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.GamesPerNumberStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.GamesPerNumberStatistics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGamesPerNumberStatisticsMapper {

    GamesPerNumberStatistics dtoToGamesPerNumberStatistics(GamesPerNumberStatisticsDto gamesPerNumberStatisticsDto);
}
