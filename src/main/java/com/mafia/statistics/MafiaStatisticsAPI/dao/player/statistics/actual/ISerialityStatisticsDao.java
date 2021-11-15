package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatisticsDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISerialityStatisticsDao extends JpaRepository<SerialityStatisticsDto, Long> {

    SerialityStatisticsDto findFirstByOrderByMaximumSeriesOfWinDesc();

    SerialityStatisticsDto findFirstByOrderByMaximumSeriesOfDefeatDesc();
}
