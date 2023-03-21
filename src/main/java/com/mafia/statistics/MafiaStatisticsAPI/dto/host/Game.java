package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Game {

    private Long id;

    private Long hostId;

    private Integer number;

    private Integer[] bestMove;

    private String startDatetime;

    private String endDatetime;

    private GameStatus status;

    private TeamWon won;

    private String note;

    private Integer blackPlayerOneId;
    private Integer blackPlayerTwoId;
    private Integer donPlayerId;
    private Integer sheriffPlayerId;
    private Integer firstShootPlayerId;

    private List<List<Integer>> players;

    private List<List<Float>> bestPlayers;

    private List<Day> days;

    private Boolean isAggregated;

    private String insertedAt;
    private String updatedAt;
}
