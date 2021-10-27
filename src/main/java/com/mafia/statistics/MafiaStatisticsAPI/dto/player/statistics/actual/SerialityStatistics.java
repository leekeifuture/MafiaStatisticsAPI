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
    private Long successivelyPlayedByRed; // with sheriff
    @NonNull
    private Long successivelyPlayedBySheriff;
    @NonNull
    private Long successivelyPlayedByBlack; // with don
    @NonNull
    private Long successivelyPlayedByDon;

    @NonNull
    private Long successivelyWonByRed; // with sheriff
    @NonNull
    private Long successivelyWonBySheriff;
    @NonNull
    private Long successivelyWonByBlack; // with don
    @NonNull
    private Long successivelyWonByDon;

    @NonNull
    private Long successivelyLostByRed; // with sheriff
    @NonNull
    private Long successivelyLostBySheriff;
    @NonNull
    private Long successivelyLostByBlack; // with don
    @NonNull
    private Long successivelyLostByDon;

    @NonNull
    private Long maximumSeriesOfWin;
    @NonNull
    private Long maximumSeriesOfDefeat;
}
