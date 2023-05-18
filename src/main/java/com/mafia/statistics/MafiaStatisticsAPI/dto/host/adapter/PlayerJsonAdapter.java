package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.*;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import java.lang.reflect.Type;

import static com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.AdapterUtil.getPlayerById;

public class PlayerJsonAdapter implements JsonSerializer<Player>, JsonDeserializer<Player> {

    @Override
    public JsonElement serialize(
            Player src,
            Type typeOfSrc,
            JsonSerializationContext context
    ) {
        return context.serialize(src);
    }

    @Override
    public Player deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        if (json instanceof JsonObject) {
            return getPlayerById(json.getAsJsonObject().get("id").getAsLong());
        } else {
            return getPlayerById(json.getAsLong());
        }
    }
}
