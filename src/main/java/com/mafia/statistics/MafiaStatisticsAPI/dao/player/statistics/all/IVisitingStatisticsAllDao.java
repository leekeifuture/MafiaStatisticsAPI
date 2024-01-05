package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.VisitingStatisticsAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVisitingStatisticsAllDao extends JpaRepository<VisitingStatisticsAll, Long> {

    List<VisitingStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW VisitingStatisticsDto(t.nickname, " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      AVG(t.byMonday), " +
            "      AVG(t.byTuesday), " +
            "      AVG(t.byWednesday), " +
            "      AVG(t.byThursday), " +
            "      AVG(t.byFriday), " +
            "      AVG(t.bySaturday), " +
            "      AVG(t.bySunday)) " +
            "FROM VisitingStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.nickname")
    List<VisitingStatisticsDto> getAggregatedData();
}
