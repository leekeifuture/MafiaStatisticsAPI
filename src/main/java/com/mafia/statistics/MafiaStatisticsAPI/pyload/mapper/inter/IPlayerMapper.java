package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPlayerMapper {

    Player dtoToPlayer(PlayerDto playerDto);

    Player dtoToPlayerMin(PlayerDto playerDto);
}
