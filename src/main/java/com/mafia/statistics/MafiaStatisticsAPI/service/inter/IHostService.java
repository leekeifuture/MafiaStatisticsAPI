package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;

import java.util.List;

public interface IHostService {

    Game getGameById(Long id);

    List<Game> getAllGames();

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);
}
