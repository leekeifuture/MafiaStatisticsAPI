package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NumbersStatistics extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private Date fromDate;
    private Date toDate;

    private Long gamesTotal;

    @OneToOne(cascade = CascadeType.ALL)
    private Place placeOne;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeTwo;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeThree;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeFour;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeFive;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeSix;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeSeven;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeEight;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeNine;
    @OneToOne(cascade = CascadeType.ALL)
    private Place placeTen;

    public NumbersStatistics(
            String nickname, Date fromDate, Date toDate, Long gamesTotal,

            Long placeOneGamesRed, Long placeOneGamesBlack, Long placeOneGamesDon,
            Long placeOneGamesSheriff, Double placeOnePercentWinRed, Double placeOnePercentWinBlack,

            Long placeTwoGamesRed, Long placeTwoGamesBlack, Long placeTwoGamesDon,
            Long placeTwoGamesSheriff, Double placeTwoPercentWinRed, Double placeTwoPercentWinBlack,

            Long placeThreeGamesRed, Long placeThreeGamesBlack, Long placeThreeGamesDon,
            Long placeThreeGamesSheriff, Double placeThreePercentWinRed, Double placeThreePercentWinBlack,

            Long placeFourGamesRed, Long placeFourGamesBlack, Long placeFourGamesDon,
            Long placeFourGamesSheriff, Double placeFourPercentWinRed, Double placeFourPercentWinBlack,

            Long placeFiveGamesRed, Long placeFiveGamesBlack, Long placeFiveGamesDon,
            Long placeFiveGamesSheriff, Double placeFivePercentWinRed, Double placeFivePercentWinBlack,

            Long placeSixGamesRed, Long placeSixGamesBlack, Long placeSixGamesDon,
            Long placeSixGamesSheriff, Double placeSixPercentWinRed, Double placeSixPercentWinBlack,

            Long placeSevenGamesRed, Long placeSevenGamesBlack, Long placeSevenGamesDon,
            Long placeSevenGamesSheriff, Double placeSevenPercentWinRed, Double placeSevenPercentWinBlack,

            Long placeEightGamesRed, Long placeEightGamesBlack, Long placeEightGamesDon,
            Long placeEightGamesSheriff, Double placeEightPercentWinRed, Double placeEightPercentWinBlack,

            Long placeNineGamesRed, Long placeNineGamesBlack, Long placeNineGamesDon,
            Long placeNineGamesSheriff, Double placeNinePercentWinRed, Double placeNinePercentWinBlack,

            Long placeTenGamesRed, Long placeTenGamesBlack, Long placeTenGamesDon,
            Long placeTenGamesSheriff, Double placeTenPercentWinRed, Double placeTenPercentWinBlack
    ) {
        this.nickname = nickname;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.gamesTotal = gamesTotal;

        this.placeOne =
                new Place(
                        placeOneGamesRed, placeOneGamesBlack,
                        placeOneGamesDon, placeOneGamesSheriff,
                        placeOnePercentWinRed, placeOnePercentWinBlack
                );
        this.placeTwo =
                new Place(
                        placeTwoGamesRed, placeTwoGamesBlack,
                        placeTwoGamesDon, placeTwoGamesSheriff,
                        placeTwoPercentWinRed, placeTwoPercentWinBlack
                );
        this.placeThree =
                new Place(
                        placeThreeGamesRed, placeThreeGamesBlack,
                        placeThreeGamesDon, placeThreeGamesSheriff,
                        placeThreePercentWinRed, placeThreePercentWinBlack
                );
        this.placeFour =
                new Place(
                        placeFourGamesRed, placeFourGamesBlack,
                        placeFourGamesDon, placeFourGamesSheriff,
                        placeFourPercentWinRed, placeFourPercentWinBlack
                );
        this.placeFive =
                new Place(
                        placeFiveGamesRed, placeFiveGamesBlack,
                        placeFiveGamesDon, placeFiveGamesSheriff,
                        placeFivePercentWinRed, placeFivePercentWinBlack
                );
        this.placeSix =
                new Place(
                        placeSixGamesRed, placeSixGamesBlack,
                        placeSixGamesDon, placeSixGamesSheriff,
                        placeSixPercentWinRed, placeSixPercentWinBlack
                );
        this.placeSeven =
                new Place(
                        placeSevenGamesRed, placeSevenGamesBlack,
                        placeSevenGamesDon, placeSevenGamesSheriff,
                        placeSevenPercentWinRed, placeSevenPercentWinBlack
                );
        this.placeEight =
                new Place(
                        placeEightGamesRed, placeEightGamesBlack,
                        placeEightGamesDon, placeEightGamesSheriff,
                        placeEightPercentWinRed, placeEightPercentWinBlack
                );
        this.placeNine =
                new Place(
                        placeNineGamesRed, placeNineGamesBlack,
                        placeNineGamesDon, placeNineGamesSheriff,
                        placeNinePercentWinRed, placeNinePercentWinBlack
                );
        this.placeTen =
                new Place(
                        placeTenGamesRed, placeTenGamesBlack,
                        placeTenGamesDon, placeTenGamesSheriff,
                        placeTenPercentWinRed, placeTenPercentWinBlack
                );
    }
}
