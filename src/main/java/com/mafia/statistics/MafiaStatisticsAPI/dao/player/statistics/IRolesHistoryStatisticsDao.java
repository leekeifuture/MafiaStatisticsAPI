package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RolesHistoryStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesHistoryStatisticsDao extends JpaRepository<RolesHistoryStatistics, Long> {

    RolesHistoryStatistics findFirstByOrderByPercentFirstShootingDesc();
}
