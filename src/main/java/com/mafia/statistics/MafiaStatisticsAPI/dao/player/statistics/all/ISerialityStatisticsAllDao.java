package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.SerialityStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerialityStatisticsAllDao extends JpaRepository<SerialityStatisticsAll, Long> {

    List<SerialityStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW SerialityStatistics(t.nickname, " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      SUM(t.gamesTotal), " +
            "      SUM(t.successivelyPlayedByRed), " +
            "      SUM(t.successivelyPlayedBySheriff), " +
            "      SUM(t.successivelyPlayedByBlack), " +
            "      SUM(t.successivelyPlayedByDon), " +
            "      SUM(t.successivelyWonByRed), " +
            "      SUM(t.successivelyWonBySheriff), " +
            "      SUM(t.successivelyWonByBlack), " +
            "      SUM(t.successivelyWonByDon), " +
            "      SUM(t.successivelyLostByRed), " +
            "      SUM(t.successivelyLostBySheriff), " +
            "      SUM(t.successivelyLostByBlack), " +
            "      SUM(t.successivelyLostByDon), " +
            "      SUM(t.maximumSeriesOfWin), " +
            "      SUM(t.maximumSeriesOfDefeat)) " +
            "FROM SerialityStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.nickname")
    List<SerialityStatistics> getAggregatedData();
}
