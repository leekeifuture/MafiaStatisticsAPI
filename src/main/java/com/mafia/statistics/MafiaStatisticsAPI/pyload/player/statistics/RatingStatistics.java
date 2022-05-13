package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingStatistics {

    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

    private Long gamesRed;
    private Long gamesBlack;
    private Long gamesDon;
    private Long gamesSheriff;

    private Long gamesWon;

    private Double bestMove;

    private Double additionalPoints; // best player / penalty

    private Double points;
}
