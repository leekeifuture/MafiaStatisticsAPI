package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Games {

    private List<Game> games;

    private Integer gamesCount;
}
