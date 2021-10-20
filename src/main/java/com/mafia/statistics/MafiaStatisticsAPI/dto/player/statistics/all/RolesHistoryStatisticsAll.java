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
public class RolesHistoryStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private Integer number;

    private String nickname;

    private Integer gamesTotal;

    private Integer gamesRed;
    private Integer gamesBlack;
    private Integer gamesDon;
    private Integer gamesSheriff;

    private Integer shooting;

    private Integer percentSelectedRed;
    private Integer percentSelectedBlack;
    private Integer percentSelectedDon;
    private Integer percentSelectedSheriff;

    private Integer percentSelectedAllRed; // with sheriff
    private Integer percentSelectedAllBlack; // with don

    private Integer percentWinningRed;
    private Integer percentWinningBlack;
    private Integer percentWinningDon;
    private Integer percentWinningSheriff;

    private Integer percentWinning;

    private Integer percentWinningAllRed; // with sheriff
    private Integer percentWinningAllBlack; // with don

    private Integer percentBestPlayer;

    private Integer percentFirstShooting;

    private Boolean isActive;
    private Date uploadingDate;
}
