package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.GamePlayer;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.AdapterUtil.getPlayerById;

public class PlayersJsonAdapter implements JsonSerializer<List<GamePlayer>>, JsonDeserializer<List<GamePlayer>> {

    @Override
    public JsonElement serialize(
            List<GamePlayer> src,
            Type typeOfSrc,
            JsonSerializationContext context
    ) {
        return context.serialize(src);
    }

    @Override
    public List<GamePlayer> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        List<GamePlayer> gamePlayers = new ArrayList<>();

        json.getAsJsonArray().forEach(playerJsonObject -> {
            Player player = null;
            Integer foulsCount = null;

            if (playerJsonObject instanceof JsonObject) {
                JsonObject gamePlayerJsonObject = playerJsonObject.getAsJsonObject();

                player = getPlayerById(
                        gamePlayerJsonObject
                                .get("player").getAsJsonObject()
                                .get("id").getAsLong()
                );
                foulsCount = gamePlayerJsonObject.get("foulsCount").getAsInt();
            } else if (playerJsonObject instanceof JsonArray) {
                JsonArray gamePlayerJsonArray = playerJsonObject.getAsJsonArray();

                player = getPlayerById(gamePlayerJsonArray.get(0).getAsLong());
                foulsCount = gamePlayerJsonArray.get(1).getAsInt();
            }

            GamePlayer gamePlayer = new GamePlayer(player, foulsCount);
            gamePlayers.add(gamePlayer);
        });

        return gamePlayers;
    }
}
