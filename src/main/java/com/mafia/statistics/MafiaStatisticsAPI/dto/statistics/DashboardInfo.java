package com.mafia.statistics.MafiaStatisticsAPI.dto.statistics;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    // Successively played

    private Long playedByRedSeriesPlayerId;
    private Sex playedByRedSeriesPlayerGender;
    private String playedByRedSeriesPlayerNickname;
    private Integer playedByRedSeriesGames;

    private Long playedBySheriffSeriesPlayerId;
    private Sex playedBySheriffSeriesPlayerGender;
    private String playedBySheriffSeriesPlayerNickname;
    private Integer playedBySheriffSeriesGames;

    private Long playedByBlackSeriesPlayerId;
    private Sex playedByBlackSeriesPlayerGender;
    private String playedByBlackSeriesPlayerNickname;
    private Integer playedByBlackSeriesGames;

    private Long playedByDonSeriesPlayerId;
    private Sex playedByDonSeriesPlayerGender;
    private String playedByDonSeriesPlayerNickname;
    private Integer playedByDonSeriesGames;

    // Successively won

    private Long wonByRedSeriesPlayerId;
    private Sex wonByRedSeriesPlayerGender;
    private String wonByRedSeriesPlayerNickname;
    private Integer wonByRedSeriesGames;

    private Long wonBySheriffSeriesPlayerId;
    private Sex wonBySheriffSeriesPlayerGender;
    private String wonBySheriffSeriesPlayerNickname;
    private Integer wonBySheriffSeriesGames;

    private Long wonByBlackSeriesPlayerId;
    private Sex wonByBlackSeriesPlayerGender;
    private String wonByBlackSeriesPlayerNickname;
    private Integer wonByBlackSeriesGames;

    private Long wonByDonSeriesPlayerId;
    private Sex wonByDonSeriesPlayerGender;
    private String wonByDonSeriesPlayerNickname;
    private Integer wonByDonSeriesGames;

    // Successively lost

    private Long lostByRedSeriesPlayerId;
    private Sex lostByRedSeriesPlayerGender;
    private String lostByRedSeriesPlayerNickname;
    private Integer lostByRedSeriesGames;

    private Long lostBySheriffSeriesPlayerId;
    private Sex lostBySheriffSeriesPlayerGender;
    private String lostBySheriffSeriesPlayerNickname;
    private Integer lostBySheriffSeriesGames;

    private Long lostByBlackSeriesPlayerId;
    private Sex lostByBlackSeriesPlayerGender;
    private String lostByBlackSeriesPlayerNickname;
    private Integer lostByBlackSeriesGames;

    private Long lostByDonSeriesPlayerId;
    private Sex lostByDonSeriesPlayerGender;
    private String lostByDonSeriesPlayerNickname;
    private Integer lostByDonSeriesGames;

    // Top tables

    private List<TopGamesTable> topGamesTable;
    private List<TopRatingTable> topRatingTable;
}
