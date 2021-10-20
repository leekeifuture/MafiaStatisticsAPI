package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

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
public class CoupleStatistics extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private Integer number;

    private String nicknameOfMafiaOne;
    private String nicknameOfMafiaTwo;

    private Integer games;

    private Integer wins;

    private Float percentOfWins;
}
