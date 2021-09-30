package com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopGamesTable {

    private Long playerId;

    private String playerNickname;

    private Integer gamesTotal;
}
