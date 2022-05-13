package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GamesPerNumberStatistics {

    private Integer number;

    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

    private Long firstShot;

    private Double percentFirstShot;

    private Double percentSelectedRed;
    private Double percentSelectedBlack;
    private Double percentSelectedDon;
    private Double percentSelectedSheriff;

    private Double percentWinningRed;
    private Double percentWinningBlack;
    private Double percentWinningDon;
    private Double percentWinningSheriff;

    private Double percentWinningAllRed; // with sheriff
    private Double percentWinningAllBlack; // with don
}
