package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.ISerialityStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.SerialityStatistics;
import org.springframework.stereotype.Component;

@Component
public class SerialityStatisticsMapperImpl implements ISerialityStatisticsMapper {

    @Override
    public SerialityStatistics dtoToSerialityStatistics(SerialityStatisticsDto serialityStatisticsDto) {
        if (serialityStatisticsDto == null) {
            return null;
        }

        return new SerialityStatistics(
                serialityStatisticsDto.getFromDate(),
                serialityStatisticsDto.getToDate(),
                serialityStatisticsDto.getSuccessivelyPlayedByRed(),
                serialityStatisticsDto.getSuccessivelyPlayedBySheriff(),
                serialityStatisticsDto.getSuccessivelyPlayedByBlack(),
                serialityStatisticsDto.getSuccessivelyPlayedByDon(),
                serialityStatisticsDto.getSuccessivelyWonByRed(),
                serialityStatisticsDto.getSuccessivelyWonBySheriff(),
                serialityStatisticsDto.getSuccessivelyWonByBlack(),
                serialityStatisticsDto.getSuccessivelyWonByDon(),
                serialityStatisticsDto.getSuccessivelyLostByRed(),
                serialityStatisticsDto.getSuccessivelyLostBySheriff(),
                serialityStatisticsDto.getSuccessivelyLostByBlack(),
                serialityStatisticsDto.getSuccessivelyLostByDon(),
                serialityStatisticsDto.getMaximumSeriesOfWin(),
                serialityStatisticsDto.getMaximumSeriesOfDefeat()
        );
    }
}
