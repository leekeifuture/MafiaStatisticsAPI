package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.VisitingStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVisitingStatisticsAllDao extends JpaRepository<VisitingStatisticsAll, Long> {

    List<VisitingStatisticsAll> findAllByIsActive(Boolean isActive);
}
