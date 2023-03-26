package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.BestPlayer;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.AdapterUtil.getPlayerById;

public class BestPlayersJsonAdapter implements JsonSerializer<List<BestPlayer>>, JsonDeserializer<List<BestPlayer>> {

    @Override
    public JsonElement serialize(List<BestPlayer> src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src);
    }

    @Override
    public List<BestPlayer> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        List<BestPlayer> gamePlayers = new ArrayList<>();

        json.getAsJsonArray().forEach(playerJsonObject -> {
            Player player = null;
            Integer additionalPoints = null;

            if (playerJsonObject instanceof JsonObject) {
                JsonObject gamePlayerJsonObject = playerJsonObject.getAsJsonObject();

                player = getPlayerById(
                        gamePlayerJsonObject
                                .get("player").getAsJsonObject()
                                .get("id").getAsLong()
                );
                additionalPoints = gamePlayerJsonObject.get("additionalPoints").getAsInt();
            } else if (playerJsonObject instanceof JsonArray) {
                JsonArray gamePlayerJsonArray = playerJsonObject.getAsJsonArray();

                player = getPlayerById(gamePlayerJsonArray.get(0).getAsLong());
                additionalPoints = gamePlayerJsonArray.get(1).getAsInt();
            }

            BestPlayer gamePlayer = new BestPlayer(player, additionalPoints);
            gamePlayers.add(gamePlayer);
        });

        return gamePlayers;
    }
}
