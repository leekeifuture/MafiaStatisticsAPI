package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IVisitingStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.VisitingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.util.DigitsUtil;

import org.springframework.stereotype.Component;

@Component
public class VisitingStatisticsMapperImpl implements IVisitingStatisticsMapper {

    @Override
    public VisitingStatistics dtoToVisitingStatistics(VisitingStatisticsDto visitingStatisticsDto) {
        if (visitingStatisticsDto == null) {
            return null;
        }

        return new VisitingStatistics(
                visitingStatisticsDto.getFromDate(),
                visitingStatisticsDto.getToDate(),
                DigitsUtil.roundDouble(visitingStatisticsDto.getByMonday(), 1),
                DigitsUtil.roundDouble(visitingStatisticsDto.getByTuesday(), 1),
                DigitsUtil.roundDouble(visitingStatisticsDto.getByWednesday(), 1),
                DigitsUtil.roundDouble(visitingStatisticsDto.getByThursday(), 1),
                DigitsUtil.roundDouble(visitingStatisticsDto.getByFriday(), 1),
                DigitsUtil.roundDouble(visitingStatisticsDto.getBySaturday(), 1),
                DigitsUtil.roundDouble(visitingStatisticsDto.getBySunday(), 1)
        );
    }
}
