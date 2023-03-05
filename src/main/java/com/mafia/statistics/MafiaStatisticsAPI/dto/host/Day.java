package com.mafia.statistics.MafiaStatisticsAPI.dto.host;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Day {

    private Long game_id;

    private Integer number;

    private List<List<Integer>> voting_map;
}
