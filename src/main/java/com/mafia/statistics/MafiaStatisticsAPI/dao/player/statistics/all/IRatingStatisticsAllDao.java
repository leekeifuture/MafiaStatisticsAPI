package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RatingStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRatingStatisticsAllDao extends JpaRepository<RatingStatisticsAll, Long> {

    List<RatingStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW RatingStatisticsDto(t.nickname, " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      SUM(t.gamesTotal), " +
            "      SUM(t.gamesRed), " +
            "      SUM(t.gamesBlack), " +
            "      SUM(t.gamesDon), " +
            "      SUM(t.gamesSheriff), " +
            "      SUM(t.bestMove), " +
            "      SUM(t.additionalPoints), " +
            "      AVG(t.points)) " +
            "FROM RatingStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.nickname")
    List<RatingStatisticsDto> getAggregatedData();
}
