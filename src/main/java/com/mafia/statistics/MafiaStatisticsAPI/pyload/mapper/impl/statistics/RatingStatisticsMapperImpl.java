package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IRatingStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.RatingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.util.DigitsUtil;

import org.springframework.stereotype.Component;

@Component
public class RatingStatisticsMapperImpl implements IRatingStatisticsMapper {

    @Override
    public RatingStatistics dtoToRatingStatistics(RatingStatisticsDto ratingStatisticsDto) {
        if (ratingStatisticsDto == null) {
            return null;
        }

        Long gamesWon = ratingStatisticsDto.getGamesRed() +
                ratingStatisticsDto.getGamesBlack() +
                ratingStatisticsDto.getGamesDon() +
                ratingStatisticsDto.getGamesSheriff();

        return new RatingStatistics(
                ratingStatisticsDto.getFromDate(),
                ratingStatisticsDto.getToDate(),
                ratingStatisticsDto.getGamesRed(),
                ratingStatisticsDto.getGamesBlack(),
                ratingStatisticsDto.getGamesDon(),
                ratingStatisticsDto.getGamesSheriff(),
                gamesWon,
                DigitsUtil.roundDouble(ratingStatisticsDto.getBestMove(), 1),
                DigitsUtil.roundDouble(ratingStatisticsDto.getAdditionalPoints(), 1),
                DigitsUtil.roundDouble(ratingStatisticsDto.getPoints(), 1)
        );
    }
}
