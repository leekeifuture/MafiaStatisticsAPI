package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RolesHistoryStatistics {

    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

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
