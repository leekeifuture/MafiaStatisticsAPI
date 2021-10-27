package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.CoupleStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICoupleStatisticsAllDao extends JpaRepository<CoupleStatisticsAll, Long> {

    List<CoupleStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW CoupleStatistics( " +
            "      t.fromDate, " +
            "      t.toDate, " +
            "      t.nicknameOfMafiaOne, " +
            "      t.nicknameOfMafiaTwo, " +
            "      t.games, " +
            "      t.wins, " +
            "      t.percentOfWins) " +
            "FROM CoupleStatisticsAll AS t " +
            "WHERE t.isActive = true")
    List<CoupleStatistics> getAggregatedData();
}
