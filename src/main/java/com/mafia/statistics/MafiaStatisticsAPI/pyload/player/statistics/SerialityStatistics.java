package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SerialityStatistics {

    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

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
