package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.List;

public interface IPlayerService {

    List<Player> getPlayers();

    List<Player> savePlayers(List<Statistics> statistics);
}
