package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.additional;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    USER("USER"),
    ADMIN("ADMIN");

    private final String value;
}
