package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IRolesHistoryStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.RolesHistoryStatistics;

import org.springframework.stereotype.Component;

@Component
public class RolesHistoryStatisticsMapperImpl implements IRolesHistoryStatisticsMapper {

    @Override
    public RolesHistoryStatistics dtoToRolesHistoryStatistics(RolesHistoryStatisticsDto rolesHistoryStatisticsDto) {
        if (rolesHistoryStatisticsDto == null) {
            return null;
        }

        return new RolesHistoryStatistics(
                rolesHistoryStatisticsDto.getFromDate(),
                rolesHistoryStatisticsDto.getToDate(),
                rolesHistoryStatisticsDto.getGamesTotal(),
                rolesHistoryStatisticsDto.getGamesRed(),
                rolesHistoryStatisticsDto.getGamesBlack(),
                rolesHistoryStatisticsDto.getGamesDon(),
                rolesHistoryStatisticsDto.getGamesSheriff(),
                rolesHistoryStatisticsDto.getShooting(),
                rolesHistoryStatisticsDto.getPercentSelectedRed(),
                rolesHistoryStatisticsDto.getPercentSelectedBlack(),
                rolesHistoryStatisticsDto.getPercentSelectedDon(),
                rolesHistoryStatisticsDto.getPercentSelectedSheriff(),
                rolesHistoryStatisticsDto.getPercentSelectedAllRed(),
                rolesHistoryStatisticsDto.getPercentSelectedAllBlack(),
                rolesHistoryStatisticsDto.getPercentWinningRed(),
                rolesHistoryStatisticsDto.getPercentWinningBlack(),
                rolesHistoryStatisticsDto.getPercentWinningDon(),
                rolesHistoryStatisticsDto.getPercentSelectedSheriff(),
                rolesHistoryStatisticsDto.getPercentWinning(),
                rolesHistoryStatisticsDto.getPercentWinningAllRed(),
                rolesHistoryStatisticsDto.getPercentWinningAllBlack(),
                rolesHistoryStatisticsDto.getPercentBestPlayer(),
                rolesHistoryStatisticsDto.getPercentFirstShooting()
        );
    }
}
