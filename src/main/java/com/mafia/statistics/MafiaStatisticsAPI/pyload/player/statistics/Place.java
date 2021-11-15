package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Place {

    private Long GamesRed;
    private Long GamesBlack;
    private Long GamesDon;
    private Long GamesSheriff;

    private Double PercentWinRed;
    private Double PercentWinBlack;
}
