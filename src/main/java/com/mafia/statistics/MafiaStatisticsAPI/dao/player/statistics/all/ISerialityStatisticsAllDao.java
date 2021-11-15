package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.SerialityStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerialityStatisticsAllDao extends JpaRepository<SerialityStatisticsAll, Long> {

    List<SerialityStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW SerialityStatisticsDto(t.nickname, " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      SUM(t.gamesTotal), " +
            "      MAX(t.successivelyPlayedByRed), " +
            "      MAX(t.successivelyPlayedBySheriff), " +
            "      MAX(t.successivelyPlayedByBlack), " +
            "      MAX(t.successivelyPlayedByDon), " +
            "      MAX(t.successivelyWonByRed), " +
            "      MAX(t.successivelyWonBySheriff), " +
            "      MAX(t.successivelyWonByBlack), " +
            "      MAX(t.successivelyWonByDon), " +
            "      MAX(t.successivelyLostByRed), " +
            "      MAX(t.successivelyLostBySheriff), " +
            "      MAX(t.successivelyLostByBlack), " +
            "      MAX(t.successivelyLostByDon), " +
            "      MAX(t.maximumSeriesOfWin), " +
            "      MAX(t.maximumSeriesOfDefeat)) " +
            "FROM SerialityStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.nickname")
    List<SerialityStatisticsDto> getAggregatedData();
}
