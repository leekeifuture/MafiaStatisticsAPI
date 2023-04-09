package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

public class DateJsonAdapter implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        Gson gson = new Gson().newBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        return gson.fromJson(json, typeOfT);
    }
}
