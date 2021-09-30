package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RatingStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRatingStatisticsDao extends JpaRepository<RatingStatistics, Long> {

    List<RatingStatistics> findTop15ByOrderByPointsDesc();
}
