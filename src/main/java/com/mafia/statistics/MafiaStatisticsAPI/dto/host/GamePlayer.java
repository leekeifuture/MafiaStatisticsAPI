package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GamePlayer {

    private Player player;

    private Integer foulsCount;
}
