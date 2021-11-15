package com.mafia.statistics.MafiaStatisticsAPI.dao.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.NumbersStatisticsAll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INumbersStatisticsAllDao extends JpaRepository<NumbersStatisticsAll, Long> {

    List<NumbersStatisticsAll> findAllByIsActive(Boolean isActive);

    @Query("SELECT NEW NumbersStatisticsDto(t.nickname, " +
            "      MIN(t.fromDate), " +
            "      MAX(t.toDate), " +
            "      SUM(t.gamesTotal), " +

            "      SUM(t.placeOne.gamesRed), " +
            "      SUM(t.placeOne.gamesBlack), " +
            "      SUM(t.placeOne.gamesDon), " +
            "      SUM(t.placeOne.gamesSheriff), " +
            "      AVG(t.placeOne.percentWinRed), " +
            "      AVG(t.placeOne.percentWinBlack), " +

            "      SUM(t.placeTwo.gamesRed), " +
            "      SUM(t.placeTwo.gamesBlack), " +
            "      SUM(t.placeTwo.gamesDon), " +
            "      SUM(t.placeTwo.gamesSheriff), " +
            "      AVG(t.placeTwo.percentWinRed), " +
            "      AVG(t.placeTwo.percentWinBlack), " +

            "      SUM(t.placeThree.gamesRed), " +
            "      SUM(t.placeThree.gamesBlack), " +
            "      SUM(t.placeThree.gamesDon), " +
            "      SUM(t.placeThree.gamesSheriff), " +
            "      AVG(t.placeThree.percentWinRed), " +
            "      AVG(t.placeThree.percentWinBlack), " +

            "      SUM(t.placeFour.gamesRed), " +
            "      SUM(t.placeFour.gamesBlack), " +
            "      SUM(t.placeFour.gamesDon), " +
            "      SUM(t.placeFour.gamesSheriff), " +
            "      AVG(t.placeFour.percentWinRed), " +
            "      AVG(t.placeFour.percentWinBlack), " +

            "      SUM(t.placeFive.gamesRed), " +
            "      SUM(t.placeFive.gamesBlack), " +
            "      SUM(t.placeFive.gamesDon), " +
            "      SUM(t.placeFive.gamesSheriff), " +
            "      AVG(t.placeFive.percentWinRed), " +
            "      AVG(t.placeFive.percentWinBlack), " +

            "      SUM(t.placeSix.gamesRed), " +
            "      SUM(t.placeSix.gamesBlack), " +
            "      SUM(t.placeSix.gamesDon), " +
            "      SUM(t.placeSix.gamesSheriff), " +
            "      AVG(t.placeSix.percentWinRed), " +
            "      AVG(t.placeSix.percentWinBlack), " +

            "      SUM(t.placeSeven.gamesRed), " +
            "      SUM(t.placeSeven.gamesBlack), " +
            "      SUM(t.placeSeven.gamesDon), " +
            "      SUM(t.placeSeven.gamesSheriff), " +
            "      AVG(t.placeSeven.percentWinRed), " +
            "      AVG(t.placeSeven.percentWinBlack), " +

            "      SUM(t.placeEight.gamesRed), " +
            "      SUM(t.placeEight.gamesBlack), " +
            "      SUM(t.placeEight.gamesDon), " +
            "      SUM(t.placeEight.gamesSheriff), " +
            "      AVG(t.placeEight.percentWinRed), " +
            "      AVG(t.placeEight.percentWinBlack), " +

            "      SUM(t.placeNine.gamesRed), " +
            "      SUM(t.placeNine.gamesBlack), " +
            "      SUM(t.placeNine.gamesDon), " +
            "      SUM(t.placeNine.gamesSheriff), " +
            "      AVG(t.placeNine.percentWinRed), " +
            "      AVG(t.placeNine.percentWinBlack), " +

            "      SUM(t.placeTen.gamesRed), " +
            "      SUM(t.placeTen.gamesBlack), " +
            "      SUM(t.placeTen.gamesDon), " +
            "      SUM(t.placeTen.gamesSheriff), " +
            "      AVG(t.placeTen.percentWinRed), " +
            "      AVG(t.placeTen.percentWinBlack)) " +
            "FROM NumbersStatisticsAll AS t " +
            "WHERE t.isActive = true " +
            "GROUP BY t.nickname")
    List<NumbersStatisticsDto> getAggregatedData();
}
