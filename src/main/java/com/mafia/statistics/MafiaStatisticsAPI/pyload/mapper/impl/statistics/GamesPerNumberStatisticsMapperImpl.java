package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.GamesPerNumberStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IGamesPerNumberStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.GamesPerNumberStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.util.DigitsUtil;

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
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentFirstShot(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentSelectedRed(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentSelectedBlack(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentSelectedDon(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentSelectedSheriff(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentWinningRed(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentWinningBlack(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentWinningDon(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentWinningSheriff(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentWinningAllRed(), 1),
                DigitsUtil.roundDouble(gamesPerNumberStatisticsDto.getPercentWinningAllBlack(), 1)
        );
    }
}
