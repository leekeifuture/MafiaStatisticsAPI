package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IRolesHistoryStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.util.DigitsUtil;

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
                rolesHistoryStatisticsDto.getGamesRed(),
                rolesHistoryStatisticsDto.getGamesBlack(),
                rolesHistoryStatisticsDto.getGamesDon(),
                rolesHistoryStatisticsDto.getGamesSheriff(),
                rolesHistoryStatisticsDto.getShooting(),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentSelectedRed(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentSelectedBlack(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentSelectedDon(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentSelectedSheriff(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentSelectedAllRed(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentSelectedAllBlack(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentWinningRed(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentWinningBlack(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentWinningDon(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentSelectedSheriff(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentWinning(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentWinningAllRed(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentWinningAllBlack(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentBestPlayer(), 1),
                DigitsUtil.roundDouble(rolesHistoryStatisticsDto.getPercentFirstShooting(), 1)
        );
    }
}
