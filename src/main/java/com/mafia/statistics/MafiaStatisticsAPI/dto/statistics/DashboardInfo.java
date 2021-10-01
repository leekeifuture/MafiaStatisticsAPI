package com.mafia.statistics.MafiaStatisticsAPI.dto.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional.TopGamesTable;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.additional.TopRatingTable;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardInfo {

    private Long winSeriesPlayerId;
    private String winSeriesPlayerNickname;
    private Integer winSeriesGames;

    private Long defeatSeriesPlayerId;
    private String defeatSeriesPlayerNickname;
    private Integer defeatSeriesGames;

    private Long visitingSeriesPlayerId;
    private String visitingSeriesPlayerNickname;
    private Integer visitingSeriesPercent;

    private Long firstShootingSeriesPlayerId;
    private String firstShootingSeriesPlayerNickname;
    private Integer firstShootingSeriesPercent;

    private List<TopGamesTable> topGamesTable;
    private List<TopRatingTable> topRatingTable;
}