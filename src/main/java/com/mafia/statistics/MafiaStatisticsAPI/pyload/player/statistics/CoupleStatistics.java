package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoupleStatistics {

    private Date fromDate;
    private Date toDate;

    private Player couplePlayer;

    private Long games;

    private Long wins;

    private Long percentOfWins;
}
