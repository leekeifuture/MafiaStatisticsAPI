package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.CoupleStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICoupleStatisticsAllDao extends JpaRepository<CoupleStatisticsAll, Long> {

    List<CoupleStatisticsAll> findAllByIsActive(Boolean isActive);
}
