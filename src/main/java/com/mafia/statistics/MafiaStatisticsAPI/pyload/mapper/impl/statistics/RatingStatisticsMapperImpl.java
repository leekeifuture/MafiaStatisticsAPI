package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IRatingStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.RatingStatistics;

import org.springframework.stereotype.Component;

@Component
public class RatingStatisticsMapperImpl implements IRatingStatisticsMapper {

    @Override
    public RatingStatistics dtoToRatingStatistics(RatingStatisticsDto ratingStatisticsDto) {
        if (ratingStatisticsDto == null) {
            return null;
        }

        return new RatingStatistics(
                ratingStatisticsDto.getNickname(),
                ratingStatisticsDto.getFromDate(),
                ratingStatisticsDto.getToDate(),
                ratingStatisticsDto.getGamesTotal(),
                ratingStatisticsDto.getGamesRed(),
                ratingStatisticsDto.getGamesBlack(),
                ratingStatisticsDto.getGamesDon(),
                ratingStatisticsDto.getGamesSheriff(),
                ratingStatisticsDto.getBestMove(),
                ratingStatisticsDto.getPenaltyPoints(),
                ratingStatisticsDto.getPoints()
        );
    }
}
