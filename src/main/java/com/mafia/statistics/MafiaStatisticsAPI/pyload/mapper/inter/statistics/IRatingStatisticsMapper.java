package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.RatingStatistics;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRatingStatisticsMapper {

    RatingStatistics dtoToRatingStatistics(RatingStatisticsDto ratingStatisticsDto);
}
