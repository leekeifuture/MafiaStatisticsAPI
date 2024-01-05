package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.*;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.BestPlayer;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.AdapterUtil.getPlayerById;

public class BestPlayersJsonAdapter implements JsonSerializer<List<BestPlayer>>, JsonDeserializer<List<BestPlayer>> {

    @Override
    public JsonElement serialize(
            List<BestPlayer> src,
            Type typeOfSrc,
            JsonSerializationContext context
    ) {
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
            Float additionalPoints = null;

            if (playerJsonObject instanceof JsonObject) {
                JsonObject gamePlayerJsonObject = playerJsonObject.getAsJsonObject();

                player = gamePlayerJsonObject.has("player")
                        ? getPlayerById(gamePlayerJsonObject
                        .get("player").getAsJsonObject()
                        .get("id").getAsLong())
                        : getPlayerById(gamePlayerJsonObject
                        .get("playerId").getAsLong());

                additionalPoints = gamePlayerJsonObject
                        .has("additionalPoints")
                        ? gamePlayerJsonObject.get("additionalPoints").getAsFloat()
                        : 0F;
            } else if (playerJsonObject instanceof JsonArray) {
                JsonArray gamePlayerJsonArray = playerJsonObject.getAsJsonArray();

                player = getPlayerById(gamePlayerJsonArray.get(0).getAsLong());
                additionalPoints = gamePlayerJsonArray.get(1).getAsFloat();
            }

            BestPlayer gamePlayer = new BestPlayer(player, additionalPoints);
            gamePlayers.add(gamePlayer);
        });

        return gamePlayers;
    }
}
