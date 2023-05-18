package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class NumbersStatistics {

    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
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
