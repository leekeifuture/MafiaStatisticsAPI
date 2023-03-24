package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameStatus {

    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private final String gameStatus;
}
