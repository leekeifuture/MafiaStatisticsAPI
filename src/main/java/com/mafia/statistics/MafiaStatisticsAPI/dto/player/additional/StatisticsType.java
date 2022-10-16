package com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatisticsType {

    NUMBERS("numbers"),
    COUPLE("couple"),
    RATING("rating"),
    ROLES_HISTORY("roles_history"),
    VISITING("visiting"),
    GAMES_PER_NUMBER("games_per_number"),
    SERIALITY("seriality"),
    UNKNOWN("unknown");

    private final String statisticsType;
}
