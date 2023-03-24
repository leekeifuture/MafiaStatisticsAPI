package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.BestPlayer;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import static com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.PlayerJsonAdapter.getPlayerById;

@Component
@RequiredArgsConstructor
public class BestPlayersJsonAdapter implements JsonDeserializer<List<BestPlayer>> {

    @Override
    public List<BestPlayer> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        List<BestPlayer> gamePlayers = new ArrayList<>();

        json.getAsJsonArray().forEach(playerJsonObject -> {
            JsonArray gamePlayerJsonArray = playerJsonObject.getAsJsonArray();

            Player player = getPlayerById(gamePlayerJsonArray.get(0).getAsLong());
            Integer additionalPoints = gamePlayerJsonArray.get(1).getAsInt();

            BestPlayer gamePlayer = new BestPlayer(player, additionalPoints);
            gamePlayers.add(gamePlayer);
        });

        return gamePlayers;
    }
}
