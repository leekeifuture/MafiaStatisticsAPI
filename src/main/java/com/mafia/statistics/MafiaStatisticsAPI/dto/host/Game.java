package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.BestPlayersJsonAdapter;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.PlayerJsonAdapter;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.PlayersJsonAdapter;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Game {

    private Long id;

    @SerializedName("hostId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player host;

    private Integer number;

    private Integer[] bestMove;

    private String startDatetime;

    private String endDatetime;

    private GameStatus status;

    private TeamWon won;

    private String note;

    @SerializedName("blackPlayerOneId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player blackPlayerOne;
    @SerializedName("blackPlayerTwoId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player blackPlayerTwo;
    @SerializedName("donPlayerId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player donPlayer;
    @SerializedName("sheriffPlayerId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player sheriffPlayer;
    @SerializedName("firstShootPlayerId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player firstShootPlayer;

    @JsonAdapter(PlayersJsonAdapter.class)
    private List<GamePlayer> players;

    @JsonAdapter(BestPlayersJsonAdapter.class)
    private List<BestPlayer> bestPlayers;

    private List<Day> days;

    private Boolean isAggregated;

    private String insertedAt;
    private String updatedAt;
}
