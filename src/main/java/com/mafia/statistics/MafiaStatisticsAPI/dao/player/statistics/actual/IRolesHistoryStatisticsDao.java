package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesHistoryStatisticsDao extends JpaRepository<RolesHistoryStatistics, Long> {

    RolesHistoryStatistics findFirstByGamesTotalGreaterThanOrderByPercentFirstShootingDesc(Long gamesTotal);
}
