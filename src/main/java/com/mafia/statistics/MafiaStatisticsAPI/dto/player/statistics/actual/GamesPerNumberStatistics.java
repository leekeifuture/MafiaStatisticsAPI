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
public class GamesPerNumberStatistics extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Integer number;

    @NonNull
    private Date fromDate;
    @NonNull
    private Date toDate;

    @NonNull
    private Long gamesTotal;

    @NonNull
    private Long firstShot;

    @NonNull
    private Double percentFirstShot;

    @NonNull
    private Double percentSelectedRed;
    @NonNull
    private Double percentSelectedBlack;
    @NonNull
    private Double percentSelectedDon;
    @NonNull
    private Double percentSelectedSheriff;

    @NonNull
    private Double percentWinningRed;
    @NonNull
    private Double percentWinningBlack;
    @NonNull
    private Double percentWinningDon;
    @NonNull
    private Double percentWinningSheriff;

    @NonNull
    private Double percentWinningAllRed; // with sheriff
    @NonNull
    private Double percentWinningAllBlack; // with don

}
