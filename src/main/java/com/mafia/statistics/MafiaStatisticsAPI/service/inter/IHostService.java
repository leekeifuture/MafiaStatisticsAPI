package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Games;
import com.mafia.statistics.MafiaStatisticsAPI.security.UserPrincipal;

public interface IHostService {

    Game getGameById(Long id);

    Games getAllGames(Integer limit, Integer page);

    Game createGame(Game game, UserPrincipal userPrincipal);

    Game updateGame(Long id, Game game);

    void deleteGame(Long id);
}
