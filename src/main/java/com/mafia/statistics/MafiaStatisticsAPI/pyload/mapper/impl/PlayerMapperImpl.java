package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.IPlayerMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.ICoupleStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.INumbersStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IRatingStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IRolesHistoryStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.ISerialityStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.IVisitingStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.additional.Role;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlayerMapperImpl implements IPlayerMapper {

    private final INumbersStatisticsMapper numbersStatisticsMapper;
    private final ICoupleStatisticsMapper coupleStatisticsMapper;
    private final IRatingStatisticsMapper ratingStatisticsMapper;
    private final IRolesHistoryStatisticsMapper rolesHistoryStatisticsMapper;
    private final IVisitingStatisticsMapper visitingStatisticsMapper;
    private final ISerialityStatisticsMapper serialityStatisticsMapper;

    @Override
    public Player dtoToPlayer(PlayerDto playerDto) {
        if (playerDto == null) {
            return null;
        }

        Set<Role> playerRoles = playerDto
                .getRoles()
                .stream().map(role -> Role.valueOf(role.getName()))
                .collect(Collectors.toSet());

        return new Player(
                playerDto.getId(),
                playerDto.getCustomNickname(),
                playerDto.getGamesTotal(),
                playerDto.getGender(),
                playerDto.getPhotoUrl(),
                playerRoles,
                numbersStatisticsMapper
                        .dtoToNumbersStatistics(playerDto.getNumbersStatistics()),
                coupleStatisticsMapper
                        .dtoToCoupleStatistics(playerDto.getCoupleStatistics()),
                ratingStatisticsMapper
                        .dtoToRatingStatistics(playerDto.getRatingStatistics()),
                rolesHistoryStatisticsMapper
                        .dtoToRolesHistoryStatistics(playerDto.getRolesHistoryStatistics()),
                visitingStatisticsMapper
                        .dtoToVisitingStatistics(playerDto.getVisitingStatistics()),
                serialityStatisticsMapper
                        .dtoToSerialityStatistics(playerDto.getSerialityStatistics())
        );
    }
}
