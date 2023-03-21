package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VotingMap {

    private Long playerId;

    private Long whoPutToVoteId;

    private Integer firstVoteCount;

    private Integer secondVoteCount;
}
