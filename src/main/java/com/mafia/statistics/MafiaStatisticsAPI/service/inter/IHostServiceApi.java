package com.mafia.statistics.MafiaStatisticsAPI.service.inter;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IHostServiceApi {

    @GET("/game/{id}")
    Call<Game> getGameById(@Path("id") Long id);

    @GET("/game")
    Call<List<Game>> getAllGames();

    @POST("/game")
    Call<Game> createGame(@Body Game game);

    @PATCH("/game/{id}")
    Call<Game> updateGame(@Path("id") Long id, @Body Game game);

    @DELETE("/game/{id}")
    Call<Object> deleteGame(@Path("id") Long id);
}
