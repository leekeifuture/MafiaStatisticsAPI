package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamWon {

    RED("RED"),
    BLACK("BLACK"),
    DRAW("DRAW");

    private final String teamWon;
}
