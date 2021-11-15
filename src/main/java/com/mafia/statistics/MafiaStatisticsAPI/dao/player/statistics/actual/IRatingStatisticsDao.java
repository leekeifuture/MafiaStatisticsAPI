package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatisticsDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRatingStatisticsDao extends JpaRepository<RatingStatisticsDto, Long> {

    List<RatingStatisticsDto> findTop15ByGamesTotalGreaterThanOrderByPointsDesc(Long gamesTotal);
}
