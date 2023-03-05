package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IHostServiceApi {

    @GET("/game/{id}")
    Call<List<Game>> getGameById(@Path("id") Long id);

    Call<List<Game>> getAllGames();

    Call<Game> createGame();

    Call<Game> updateGame();
}
