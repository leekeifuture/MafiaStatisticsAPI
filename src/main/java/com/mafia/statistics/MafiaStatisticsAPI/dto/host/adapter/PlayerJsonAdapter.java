package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.IPlayerMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;
import com.mafia.statistics.MafiaStatisticsAPI.util.ApplicationContextUtils;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlayerJsonAdapter implements JsonDeserializer<Player> {

    @Override
    public Player deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException {
        return getPlayerById(json.getAsLong());
    }

    static Player getPlayerById(Long id) {
        ApplicationContext appCtx = ApplicationContextUtils
                .getApplicationContext();

        IPlayerService playerService = appCtx
                .getBean("playerService", IPlayerService.class);
        IPlayerMapper playerMapper = appCtx
                .getBean("playerMapperImpl", IPlayerMapper.class);

        return playerMapper.dtoToPlayerMin(
                playerService.getPlayerById(id)
        );
    }
}
