package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Games;

import java.util.List;

public interface IHostService {

    Game getGameById(Long id);

    Games getAllGames(Integer limit, Integer page);

    Game createGame(Game game);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);
}
