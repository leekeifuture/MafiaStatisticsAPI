package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Day {

    private Long gameId;

    private Integer number;

    private List<VotingMap> votingMap;
}
