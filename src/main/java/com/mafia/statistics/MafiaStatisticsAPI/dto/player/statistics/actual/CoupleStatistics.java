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
public class CoupleStatistics extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Date fromDate;
    @NonNull
    private Date toDate;

    @NonNull
    private String nicknameOfMafiaOne;
    @NonNull
    private String nicknameOfMafiaTwo;

    @NonNull
    private Long games;

    @NonNull
    private Long wins;

    @NonNull
    private Double percentOfWins;
}
