package com.mafia.statistics.MafiaStatisticsAPI.service.impl;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostService;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostServiceApi;

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

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final IHostServiceApi hostServiceApi =
            retrofit.create(IHostServiceApi.class);

    @Override
    public List<Game> getGameById(Long id) {
        Call<List<Game>> retrofitCall = hostServiceApi.getGameById(id);

        Response<List<Game>> response;
        try {
            response = retrofitCall.execute();
        } catch (IOException e) {
            throw new ResourceNotFoundException("Game", "id", id);
        }

        return response.body();
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public Game createGame() {
        return null;
    }

    @Override
    public Game updateGame() {
        return null;
    }
}
