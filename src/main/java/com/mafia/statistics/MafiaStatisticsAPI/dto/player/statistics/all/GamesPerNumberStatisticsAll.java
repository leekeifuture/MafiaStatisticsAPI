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
public class GamesPerNumberStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private Integer number;

    private Integer gamesTotal;

    private Integer firstShot;

    private Integer percentFirstShot;

    private Integer percentSelectedRed;
    private Integer percentSelectedBlack;
    private Integer percentSelectedDon;
    private Integer percentSelectedSheriff;

    private Integer percentWinningRed;
    private Integer percentWinningBlack;
    private Integer percentWinningDon;
    private Integer percentWinningSheriff;

    private Integer percentWinningAllRed; // with sheriff
    private Integer percentWinningAllBlack; // with don

    private Boolean isActive;
    private Date uploadingDate;
}
