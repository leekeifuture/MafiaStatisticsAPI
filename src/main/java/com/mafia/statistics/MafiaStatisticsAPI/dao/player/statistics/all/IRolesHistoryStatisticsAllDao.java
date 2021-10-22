package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RolesHistoryStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRolesHistoryStatisticsAllDao extends JpaRepository<RolesHistoryStatisticsAll, Long> {

    List<RolesHistoryStatisticsAll> findAllByIsActive(Boolean isActive);
}
