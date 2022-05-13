package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class NumbersStatisticsDto extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

    private Long gamesTotal;

    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeOne;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeTwo;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeThree;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeFour;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeFive;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeSix;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeSeven;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeEight;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeNine;
    @OneToOne(cascade = CascadeType.ALL)
    private PlaceDto placeTen;

    public NumbersStatisticsDto(
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
                new PlaceDto(
                        placeOneGamesRed, placeOneGamesBlack,
                        placeOneGamesDon, placeOneGamesSheriff,
                        placeOnePercentWinRed, placeOnePercentWinBlack
                );
        this.placeTwo =
                new PlaceDto(
                        placeTwoGamesRed, placeTwoGamesBlack,
                        placeTwoGamesDon, placeTwoGamesSheriff,
                        placeTwoPercentWinRed, placeTwoPercentWinBlack
                );
        this.placeThree =
                new PlaceDto(
                        placeThreeGamesRed, placeThreeGamesBlack,
                        placeThreeGamesDon, placeThreeGamesSheriff,
                        placeThreePercentWinRed, placeThreePercentWinBlack
                );
        this.placeFour =
                new PlaceDto(
                        placeFourGamesRed, placeFourGamesBlack,
                        placeFourGamesDon, placeFourGamesSheriff,
                        placeFourPercentWinRed, placeFourPercentWinBlack
                );
        this.placeFive =
                new PlaceDto(
                        placeFiveGamesRed, placeFiveGamesBlack,
                        placeFiveGamesDon, placeFiveGamesSheriff,
                        placeFivePercentWinRed, placeFivePercentWinBlack
                );
        this.placeSix =
                new PlaceDto(
                        placeSixGamesRed, placeSixGamesBlack,
                        placeSixGamesDon, placeSixGamesSheriff,
                        placeSixPercentWinRed, placeSixPercentWinBlack
                );
        this.placeSeven =
                new PlaceDto(
                        placeSevenGamesRed, placeSevenGamesBlack,
                        placeSevenGamesDon, placeSevenGamesSheriff,
                        placeSevenPercentWinRed, placeSevenPercentWinBlack
                );
        this.placeEight =
                new PlaceDto(
                        placeEightGamesRed, placeEightGamesBlack,
                        placeEightGamesDon, placeEightGamesSheriff,
                        placeEightPercentWinRed, placeEightPercentWinBlack
                );
        this.placeNine =
                new PlaceDto(
                        placeNineGamesRed, placeNineGamesBlack,
                        placeNineGamesDon, placeNineGamesSheriff,
                        placeNinePercentWinRed, placeNinePercentWinBlack
                );
        this.placeTen =
                new PlaceDto(
                        placeTenGamesRed, placeTenGamesBlack,
                        placeTenGamesDon, placeTenGamesSheriff,
                        placeTenPercentWinRed, placeTenPercentWinBlack
                );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        NumbersStatisticsDto that = (NumbersStatisticsDto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
