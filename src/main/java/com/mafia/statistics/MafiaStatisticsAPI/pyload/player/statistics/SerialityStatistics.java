package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SerialityStatistics {

    private String nickname;

    private Date fromDate;
    private Date toDate;

    private Long gamesTotal;

    private Integer successivelyPlayedByRed; // with sheriff
    private Integer successivelyPlayedBySheriff;
    private Integer successivelyPlayedByBlack; // with don
    private Integer successivelyPlayedByDon;

    private Integer successivelyWonByRed; // with sheriff
    private Integer successivelyWonBySheriff;
    private Integer successivelyWonByBlack; // with don
    private Integer successivelyWonByDon;

    private Integer successivelyLostByRed; // with sheriff
    private Integer successivelyLostBySheriff;
    private Integer successivelyLostByBlack; // with don
    private Integer successivelyLostByDon;

    private Integer maximumSeriesOfWin;
    private Integer maximumSeriesOfDefeat;
}
