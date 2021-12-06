package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import java.util.List;

public interface IPlayerService {

    List<PlayerDto> getPlayers();

    void savePlayers(List<Statistics> statistics);

    List<Player> searchPlayersByNickname(String query);

    PlayerDto getPlayerById(Long id);

    PlayerDto getPlayerByNickname(String nickname);
}
