package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RolesHistoryStatistics {

    private Date fromDate;
    private Date toDate;

    private Long gamesTotal;

    private Long gamesRed;
    private Long gamesBlack;
    private Long gamesDon;
    private Long gamesSheriff;

    private Long shooting;

    private Double percentSelectedRed;
    private Double percentSelectedBlack;
    private Double percentSelectedDon;
    private Double percentSelectedSheriff;

    private Double percentSelectedAllRed; // with sheriff
    private Double percentSelectedAllBlack; // with don

    private Double percentWinningRed;
    private Double percentWinningBlack;
    private Double percentWinningDon;
    private Double percentWinningSheriff;

    private Double percentWinning;

    private Double percentWinningAllRed; // with sheriff
    private Double percentWinningAllBlack; // with don

    private Double percentBestPlayer;

    private Double percentFirstShooting;
}
