package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameStatus {

    IN_PROGRESS("in_progress"),
    COMPLETED("completed");

    private final String gameStatus;
}
