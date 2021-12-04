package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoupleStatistics {

    private Date fromDate;
    private Date toDate;

    private String nicknameOfMafiaOne;
    private String nicknameOfMafiaTwo;

    private Long games;

    private Long wins;

    private Long percentOfWins;
}
