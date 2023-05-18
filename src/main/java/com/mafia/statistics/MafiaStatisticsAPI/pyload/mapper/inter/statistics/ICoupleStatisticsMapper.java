package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.CoupleStatistics;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICoupleStatisticsMapper {

    List<CoupleStatistics> dtoToCoupleStatistics(
            List<CoupleStatisticsDto> coupleStatisticsDto,
            String playerNickname
    );
}
