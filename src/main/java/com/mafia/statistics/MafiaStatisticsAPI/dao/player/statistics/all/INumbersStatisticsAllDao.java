package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.NumbersStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INumbersStatisticsAllDao extends JpaRepository<NumbersStatisticsAll, Long> {

    List<NumbersStatisticsAll> findAllByIsActive(Boolean isActive);
}
