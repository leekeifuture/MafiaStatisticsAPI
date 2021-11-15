package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RolesHistoryStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRolesHistoryStatisticsAllDao extends JpaRepository<RolesHistoryStatisticsAll, Long> {

    List<RolesHistoryStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW RolesHistoryStatisticsDto(t.nickname, " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      SUM(t.gamesTotal), " +
            "      SUM(t.gamesRed), " +
            "      SUM(t.gamesBlack), " +
            "      SUM(t.gamesDon), " +
            "      SUM(t.gamesSheriff), " +
            "      SUM(t.shooting), " +
            "      AVG(t.percentSelectedRed), " +
            "      AVG(t.percentSelectedBlack), " +
            "      AVG(t.percentSelectedDon), " +
            "      AVG(t.percentSelectedSheriff), " +
            "      AVG(t.percentSelectedAllRed), " +
            "      AVG(t.percentSelectedAllBlack), " +
            "      AVG(t.percentWinningRed), " +
            "      AVG(t.percentWinningBlack), " +
            "      AVG(t.percentWinningDon), " +
            "      AVG(t.percentWinningSheriff), " +
            "      AVG(t.percentWinning), " +
            "      AVG(t.percentWinningAllRed), " +
            "      AVG(t.percentWinningAllBlack), " +
            "      AVG(t.percentBestPlayer), " +
            "      AVG(t.percentFirstShooting)) " +
            "FROM RolesHistoryStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.nickname")
    List<RolesHistoryStatisticsDto> getAggregatedData();
}
