package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRatingStatisticsDao extends JpaRepository<RatingStatistics, Long> {

    List<RatingStatistics> findTop15ByGamesTotalGreaterThanOrderByPointsDesc(Integer gamesTotal);
}
