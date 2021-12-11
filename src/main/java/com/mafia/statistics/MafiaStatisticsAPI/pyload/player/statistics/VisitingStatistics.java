package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisitingStatistics {

    private Date fromDate;
    private Date toDate;

    private Double byMonday;

    private Double byTuesday;

    private Double byWednesday;

    private Double byThursday;

    private Double byFriday;

    private Double bySaturday;

    private Double bySunday;
}
