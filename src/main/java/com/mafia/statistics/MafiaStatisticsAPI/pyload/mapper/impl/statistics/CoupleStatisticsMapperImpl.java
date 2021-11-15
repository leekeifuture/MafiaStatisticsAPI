package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.ICoupleStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.CoupleStatistics;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CoupleStatisticsMapperImpl implements ICoupleStatisticsMapper {

    @Override
    public List<CoupleStatistics> dtoToCoupleStatistics(List<CoupleStatisticsDto> coupleStatisticsDto) {
        if (coupleStatisticsDto == null) {
            return null;
        }

        List<CoupleStatistics> coupleStatistics = new ArrayList<>();

        coupleStatisticsDto.forEach(statistics -> coupleStatistics.add(
                new CoupleStatistics(
                        statistics.getFromDate(),
                        statistics.getToDate(),
                        statistics.getNicknameOfMafiaOne(),
                        statistics.getNicknameOfMafiaTwo(),
                        statistics.getGames(),
                        statistics.getWins(),
                        statistics.getPercentOfWins()
                )
        ));

        return coupleStatistics;
    }
}
