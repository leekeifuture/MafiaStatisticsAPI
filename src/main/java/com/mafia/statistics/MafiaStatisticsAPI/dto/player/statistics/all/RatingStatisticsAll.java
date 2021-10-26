package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

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
public class RatingStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nickname;

    @NonNull
    private Date fromDate;
    @NonNull
    private Date toDate;

    private Integer number;

    @NonNull
    private Long gamesTotal;

    @NonNull
    private Long gamesRed;
    @NonNull
    private Long gamesBlack;
    @NonNull
    private Long gamesDon;
    @NonNull
    private Long gamesSheriff;

    @NonNull
    private Double bestMove;

    @NonNull
    private Double penaltyPoints; // best player / penalty

    @NonNull
    private Double points;

    private Boolean isActive;
    private Date uploadingDate;
}
