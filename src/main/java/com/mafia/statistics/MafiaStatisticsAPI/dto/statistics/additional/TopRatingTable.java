package com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional;

import com.vk.api.sdk.objects.base.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopRatingTable {

    private Long playerId;

    private Sex gender;

    private String playerNickname;

    private Float points;
}
