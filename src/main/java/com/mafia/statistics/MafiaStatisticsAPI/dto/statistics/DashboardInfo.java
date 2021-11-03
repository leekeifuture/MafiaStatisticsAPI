package com.mafia.statistics.MafiaStatisticsAPI.dto.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional.TopGamesTable;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional.TopRatingTable;
import com.vk.api.sdk.objects.base.Sex;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardInfo {

    private Long winSeriesPlayerId;
    private Sex winSeriesPlayerGender;
    private String winSeriesPlayerNickname;
    private Integer winSeriesGames;

    private Long defeatSeriesPlayerId;
    private Sex defeatSeriesPlayerGender;
    private String defeatSeriesPlayerNickname;
    private Integer defeatSeriesGames;

    private Long visitingSeriesPlayerId;
    private Sex visitingSeriesPlayerGender;
    private String visitingSeriesPlayerNickname;
    private Double visitingSeriesPercent;

    private Long firstShootingSeriesPlayerId;
    private Sex firstShootingSeriesPlayerGender;
    private String firstShootingSeriesPlayerNickname;
    private Double firstShootingSeriesPercent;

    private List<TopGamesTable> topGamesTable;
    private List<TopRatingTable> topRatingTable;
}
