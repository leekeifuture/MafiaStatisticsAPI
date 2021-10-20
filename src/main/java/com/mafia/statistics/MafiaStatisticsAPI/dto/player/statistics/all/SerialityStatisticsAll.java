package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SerialityStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private String nickname;

    private Integer gamesTotal;

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

    private Boolean isActive;
    private Date uploadingDate;
}
