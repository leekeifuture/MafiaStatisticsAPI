package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.GamesPerNumberStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGamesPerNumberStatisticsAllDao extends JpaRepository<GamesPerNumberStatisticsAll, Long> {
}
