package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;

import java.util.List;

public interface IHostService {

    Game createGame();

    Game updateGame();

    List<Game> getGameById(Long id);

    List<Game> getAllGames();
}
