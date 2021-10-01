package com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional;

import com.vk.api.sdk.objects.base.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopGamesTable {

    private Long playerId;

    private Sex gender;

    private String playerNickname;

    private Integer gamesTotal;
}
