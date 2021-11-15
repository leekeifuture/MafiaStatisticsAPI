package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.List;

public interface IPlayerService {

    List<PlayerDto> getPlayers();

    void savePlayers(List<Statistics> statistics);

    PlayerDto getPlayerById(Long id);

    PlayerDto getPlayerByNickname(String nickname);
}
