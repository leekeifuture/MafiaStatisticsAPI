package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.SerialityStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISerialityStatisticsDao extends JpaRepository<SerialityStatistics, Long> {

    SerialityStatistics findFirstByOrderByMaximumSeriesOfWinDesc();

    SerialityStatistics findFirstByOrderByMaximumSeriesOfDefeatDesc();
}
