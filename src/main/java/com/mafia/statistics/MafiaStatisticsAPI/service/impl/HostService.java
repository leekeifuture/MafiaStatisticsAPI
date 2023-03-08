package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostServiceApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
@RequiredArgsConstructor
public class HostService implements IHostService {

    @Value("${app.hostService.baseUrl}")
    private static String hostServiceUrl;

    private static IHostServiceApi getHostServiceApi() {
        return new Retrofit.Builder()
                .baseUrl(hostServiceUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IHostServiceApi.class);
    }

    @Override
    public Game getGameById(Long id) {
        Call<Game> retrofitCall = getHostServiceApi().getGameById(id);

        Response<Game> response;
        try {
            response = retrofitCall.execute();
        } catch (IOException e) {
            throw new ResourceNotFoundException("Game", "id", id);
        }

        return response.body();
    }

    @Override
    public List<Game> getAllGames() {
        Call<List<Game>> retrofitCall = getHostServiceApi().getAllGames();

        Response<List<Game>> response;
        try {
            response = retrofitCall.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    @Override
    public Game createGame(Game game) {
        Call<Game> retrofitCall = getHostServiceApi().createGame(game);

        Response<Game> response;
        try {
            response = retrofitCall.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    @Override
    public Game updateGame(Long id, Game game) {
        Call<Game> retrofitCall = getHostServiceApi().updateGame(id, game);

        Response<Game> response;
        try {
            response = retrofitCall.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }

    @Override
    public void deleteGame(Long id) {
        Call<Game> retrofitCall = getHostServiceApi().deleteGame(id);

        try {
            retrofitCall.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
