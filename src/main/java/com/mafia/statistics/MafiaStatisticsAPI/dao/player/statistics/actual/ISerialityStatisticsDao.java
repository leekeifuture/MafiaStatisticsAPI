package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatisticsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISerialityStatisticsDao extends JpaRepository<SerialityStatisticsDto, Long> {

    SerialityStatisticsDto findFirstByOrderBySuccessivelyPlayedByRedDesc(); // with sheriff

    SerialityStatisticsDto findFirstByOrderBySuccessivelyPlayedBySheriffDesc();

    SerialityStatisticsDto findFirstByOrderBySuccessivelyPlayedByBlackDesc(); // with don

    SerialityStatisticsDto findFirstByOrderBySuccessivelyPlayedByDonDesc();

    SerialityStatisticsDto findFirstByOrderBySuccessivelyWonByRedDesc(); // with sheriff

    SerialityStatisticsDto findFirstByOrderBySuccessivelyWonBySheriffDesc();

    SerialityStatisticsDto findFirstByOrderBySuccessivelyWonByBlackDesc(); // with don

    SerialityStatisticsDto findFirstByOrderBySuccessivelyWonByDonDesc();

    SerialityStatisticsDto findFirstByOrderBySuccessivelyLostByRedDesc(); // with sheriff

    SerialityStatisticsDto findFirstByOrderBySuccessivelyLostBySheriffDesc();

    SerialityStatisticsDto findFirstByOrderBySuccessivelyLostByBlackDesc(); // with don

    SerialityStatisticsDto findFirstByOrderBySuccessivelyLostByDonDesc();

    SerialityStatisticsDto findFirstByOrderByMaximumSeriesOfWinDesc();

    SerialityStatisticsDto findFirstByOrderByMaximumSeriesOfDefeatDesc();
}
