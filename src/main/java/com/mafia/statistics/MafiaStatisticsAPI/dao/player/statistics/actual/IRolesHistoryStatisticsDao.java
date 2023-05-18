package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatisticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesHistoryStatisticsDao extends JpaRepository<RolesHistoryStatisticsDto, Long> {

    RolesHistoryStatisticsDto findFirstByGamesTotalGreaterThanOrderByPercentFirstShootingDesc(Long gamesTotal);
}
