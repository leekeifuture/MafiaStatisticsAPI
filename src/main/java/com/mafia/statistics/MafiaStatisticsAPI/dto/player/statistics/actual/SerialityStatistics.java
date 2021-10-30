package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class SerialityStatistics extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nickname;

    @NonNull
    private Date fromDate;
    @NonNull
    private Date toDate;

    @NonNull
    private Long gamesTotal;

    @NonNull
    private Integer successivelyPlayedByRed; // with sheriff
    @NonNull
    private Integer successivelyPlayedBySheriff;
    @NonNull
    private Integer successivelyPlayedByBlack; // with don
    @NonNull
    private Integer successivelyPlayedByDon;

    @NonNull
    private Integer successivelyWonByRed; // with sheriff
    @NonNull
    private Integer successivelyWonBySheriff;
    @NonNull
    private Integer successivelyWonByBlack; // with don
    @NonNull
    private Integer successivelyWonByDon;

    @NonNull
    private Integer successivelyLostByRed; // with sheriff
    @NonNull
    private Integer successivelyLostBySheriff;
    @NonNull
    private Integer successivelyLostByBlack; // with don
    @NonNull
    private Integer successivelyLostByDon;

    @NonNull
    private Integer maximumSeriesOfWin;
    @NonNull
    private Integer maximumSeriesOfDefeat;
}
