package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NumbersStatistics {

    private Date fromDate;
    private Date toDate;

    private Place placeOne;
    private Place placeTwo;
    private Place placeThree;
    private Place placeFour;
    private Place placeFive;
    private Place placeSix;
    private Place placeSeven;
    private Place placeEight;
    private Place placeNine;
    private Place placeTen;
}
