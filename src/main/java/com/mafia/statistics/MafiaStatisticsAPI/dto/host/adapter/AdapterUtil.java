package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.IPlayerMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;
import com.mafia.statistics.MafiaStatisticsAPI.util.ApplicationContextUtils;

import org.springframework.context.ApplicationContext;

public class AdapterUtil {

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
