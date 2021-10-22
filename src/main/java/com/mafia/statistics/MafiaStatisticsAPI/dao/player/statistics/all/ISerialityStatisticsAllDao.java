package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.SerialityStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerialityStatisticsAllDao extends JpaRepository<SerialityStatisticsAll, Long> {

    List<SerialityStatisticsAll> findAllByIsActive(Boolean isActive);
}
