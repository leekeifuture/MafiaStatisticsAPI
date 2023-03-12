package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostServiceApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class HostService implements IHostService {

    private final IHostServiceApi hostServiceApi;

    public HostService(
            @Value("${app.hostService.baseUrl}") String hostServiceBaseUrl
    ) {
        this.hostServiceApi = new Retrofit.Builder()
                .baseUrl(hostServiceBaseUrl)
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder()
                                        .setFieldNamingPolicy(FieldNamingPolicy
                                                .LOWER_CASE_WITH_UNDERSCORES)
                                        .create()
                        )
                )
                .build()
                .create(IHostServiceApi.class);
    }

    @Override
    public Game getGameById(Long id) {
        Call<Game> retrofitCall = hostServiceApi.getGameById(id);

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
        Call<List<Game>> retrofitCall = hostServiceApi.getAllGames();

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
        Call<Game> retrofitCall = hostServiceApi.createGame(game);

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
        Call<Game> retrofitCall = hostServiceApi.updateGame(id, game);

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
        Call<Game> retrofitCall = hostServiceApi.deleteGame(id);

        try {
            retrofitCall.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
