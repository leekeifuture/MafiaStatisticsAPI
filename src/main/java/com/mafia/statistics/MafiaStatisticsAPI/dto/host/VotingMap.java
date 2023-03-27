package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter.PlayerJsonAdapter;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VotingMap {

    @SerializedName(value = "player", alternate = "playerId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player player;

    @SerializedName(value = "whoPutToVote", alternate = "whoPutToVoteId")
    @JsonAdapter(PlayerJsonAdapter.class)
    private Player whoPutToVote;

    private Integer firstVoteCount;

    private Integer secondVoteCount;
}
