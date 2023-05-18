package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Games {

    private List<Game> games;

    private Integer gamesCount;
}
