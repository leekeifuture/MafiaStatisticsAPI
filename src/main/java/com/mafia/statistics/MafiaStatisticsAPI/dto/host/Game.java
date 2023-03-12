package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Game {

    @SerializedName("id")
    private Long id;

    @SerializedName("host_id")
    private Long hostId;

    @SerializedName("number")
    private Integer number;

    @SerializedName("best_move")
    private Integer[] bestMove;

    @SerializedName("start_datetime")
    private String startDatetime;

    @SerializedName("end_datetime")
    private String endDatetime;

    @SerializedName("status")
    private GameStatus status;

    @SerializedName("won")
    private TeamWon won;

    @SerializedName("note")
    private String note;

    @SerializedName("black_player_one_id")
    private Integer blackPlayerOneId;
    @SerializedName("black_player_two_id")
    private Integer blackPlayerTwoId;
    @SerializedName("don_player_id")
    private Integer donPlyerId;
    @SerializedName("sheriff_player_id")
    private Integer sheriffPlayerId;
    @SerializedName("first_shoot_player_id")
    private Integer firstShootPlayerId;

    @SerializedName("players")
    private List<List<Integer>> players;

    @SerializedName("best_players")
    private List<List<Float>> best_players;

    @SerializedName("days")
    private List<Day> days;

    @SerializedName("is_aggregated")
    private Boolean isAggregated;

    @SerializedName("inserted_at")
    private String insertedAt;
    @SerializedName("updated_at")
    private String updatedAt;
}
