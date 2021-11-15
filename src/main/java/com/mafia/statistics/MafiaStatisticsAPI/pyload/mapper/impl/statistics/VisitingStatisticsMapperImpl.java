package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IVisitingStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.VisitingStatistics;

import org.springframework.stereotype.Component;

@Component
public class VisitingStatisticsMapperImpl implements IVisitingStatisticsMapper {

    @Override
    public VisitingStatistics dtoToVisitingStatistics(VisitingStatisticsDto visitingStatisticsDto) {
        if (visitingStatisticsDto == null) {
            return null;
        }

        return new VisitingStatistics(
                visitingStatisticsDto.getNickname(),
                visitingStatisticsDto.getFromDate(),
                visitingStatisticsDto.getToDate(),
                visitingStatisticsDto.getByMonday(),
                visitingStatisticsDto.getByTuesday(),
                visitingStatisticsDto.getByWednesday(),
                visitingStatisticsDto.getByThursday(),
                visitingStatisticsDto.getByFriday(),
                visitingStatisticsDto.getBySaturday(),
                visitingStatisticsDto.getBySunday()
        );
    }
}
