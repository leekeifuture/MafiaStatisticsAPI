package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatisticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoupleStatisticsDao extends JpaRepository<CoupleStatisticsDto, Long> {
}
