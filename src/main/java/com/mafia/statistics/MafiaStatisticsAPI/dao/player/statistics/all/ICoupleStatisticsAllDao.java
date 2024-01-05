package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.CoupleStatisticsAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICoupleStatisticsAllDao extends JpaRepository<CoupleStatisticsAll, Long> {

    List<CoupleStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW CoupleStatisticsDto( " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      t.nicknameOfMafiaOne, " +
            "      t.nicknameOfMafiaTwo, " +
            "      SUM(t.games), " +
            "      SUM(t.wins), " +
            "      AVG(t.percentOfWins)) " +
            "FROM CoupleStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.nicknameOfMafiaOne, t.nicknameOfMafiaTwo " +
            "ORDER BY SUM(t.wins) DESC")
    List<CoupleStatisticsDto> getAggregatedData();
}
