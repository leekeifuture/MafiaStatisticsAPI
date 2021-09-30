package com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopRatingTable {

    private Long playerId;

    private String playerNickname;

    private Float points;
}
