package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.GamesPerNumberStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.GamesPerNumberStatisticsAll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGamesPerNumberStatisticsAllDao extends JpaRepository<GamesPerNumberStatisticsAll, Long> {

    List<GamesPerNumberStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW GamesPerNumberStatisticsDto(t.number, " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      SUM(t.gamesTotal), " +
            "      SUM(t.firstShot), " +
            "      AVG(t.percentFirstShot), " +
            "      AVG(t.percentSelectedRed), " +
            "      AVG(t.percentSelectedBlack), " +
            "      AVG(t.percentSelectedDon), " +
            "      AVG(t.percentSelectedSheriff), " +
            "      AVG(t.percentWinningRed), " +
            "      AVG(t.percentWinningBlack), " +
            "      AVG(t.percentWinningDon), " +
            "      AVG(t.percentWinningSheriff), " +
            "      AVG(t.percentWinningAllRed), " +
            "      AVG(t.percentWinningAllBlack)) " +
            "FROM GamesPerNumberStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.number")
    List<GamesPerNumberStatisticsDto> getAggregatedData();
}
