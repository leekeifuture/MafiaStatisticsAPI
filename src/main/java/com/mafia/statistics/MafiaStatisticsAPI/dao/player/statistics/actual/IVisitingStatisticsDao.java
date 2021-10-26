package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IVisitingStatisticsDao extends JpaRepository<VisitingStatistics, Long> {

    @Query(value = "SELECT t2.nickname, (sum / 5) AS total " +
            "FROM ( " +
            "         SELECT nickname, " +
            "                (t.by_tuesday + " +
            "                 t.by_wednesday + " +
            "                 t.by_thursday + " +
            "                 t.by_friday + " +
            "                 t.by_sunday) AS sum " +
            "         FROM visiting_statistics AS t " +
            "     ) AS t2 " +
            "ORDER BY total DESC " +
            "LIMIT 1", nativeQuery = true)
    Object findMostVisitedPlayer();
}
