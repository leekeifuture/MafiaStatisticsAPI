package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Day {

    private Long gameId;

    private Integer number;

    private List<VotingMap> votingMap;
}