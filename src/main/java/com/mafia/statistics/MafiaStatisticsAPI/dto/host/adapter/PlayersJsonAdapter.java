package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.GamePlayer;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import static com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.PlayerJsonAdapter.getPlayerById;

@Component
@RequiredArgsConstructor
public class PlayersJsonAdapter implements JsonDeserializer<List<GamePlayer>> {

    @Override
    public List<GamePlayer> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        List<GamePlayer> gamePlayers = new ArrayList<>();

        json.getAsJsonArray().forEach(playerJsonObject -> {
            JsonArray gamePlayerJsonArray = playerJsonObject.getAsJsonArray();

            Player player = getPlayerById(gamePlayerJsonArray.get(0).getAsLong());
            Integer foulsCount = gamePlayerJsonArray.get(1).getAsInt();

            GamePlayer gamePlayer = new GamePlayer(player, foulsCount);
            gamePlayers.add(gamePlayer);
        });

        return gamePlayers;
    }
}
