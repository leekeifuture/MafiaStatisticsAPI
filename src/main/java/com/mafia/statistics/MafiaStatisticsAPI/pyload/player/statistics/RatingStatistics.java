package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingStatistics {

    private String nickname;

    private Date fromDate;
    private Date toDate;

    private Long gamesTotal;

    private Long gamesRed;
    private Long gamesBlack;
    private Long gamesDon;
    private Long gamesSheriff;

    private Double bestMove;

    private Double additionalPoints; // best player / penalty

    private Double points;
}
