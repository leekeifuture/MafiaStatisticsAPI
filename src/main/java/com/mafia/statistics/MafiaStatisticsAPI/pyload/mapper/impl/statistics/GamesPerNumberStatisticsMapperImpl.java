package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.GamesPerNumberStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IGamesPerNumberStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.GamesPerNumberStatistics;

import org.springframework.stereotype.Component;

@Component
public class GamesPerNumberStatisticsMapperImpl implements IGamesPerNumberStatisticsMapper {

    @Override
    public GamesPerNumberStatistics dtoToGamesPerNumberStatistics(GamesPerNumberStatisticsDto gamesPerNumberStatisticsDto) {
        return new GamesPerNumberStatistics(
                gamesPerNumberStatisticsDto.getNumber(),
                gamesPerNumberStatisticsDto.getFromDate(),
                gamesPerNumberStatisticsDto.getToDate(),
                gamesPerNumberStatisticsDto.getGamesTotal(),
                gamesPerNumberStatisticsDto.getFirstShot(),
                gamesPerNumberStatisticsDto.getPercentFirstShot(),
                gamesPerNumberStatisticsDto.getPercentSelectedRed(),
                gamesPerNumberStatisticsDto.getPercentSelectedBlack(),
                gamesPerNumberStatisticsDto.getPercentSelectedDon(),
                gamesPerNumberStatisticsDto.getPercentSelectedSheriff(),
                gamesPerNumberStatisticsDto.getPercentWinningRed(),
                gamesPerNumberStatisticsDto.getPercentWinningBlack(),
                gamesPerNumberStatisticsDto.getPercentWinningDon(),
                gamesPerNumberStatisticsDto.getPercentWinningSheriff(),
                gamesPerNumberStatisticsDto.getPercentWinningAllRed(),
                gamesPerNumberStatisticsDto.getPercentWinningAllBlack()
        );
    }
}
