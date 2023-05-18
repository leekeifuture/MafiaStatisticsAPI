package com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class VisitingStatistics {

    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

    private Double byMonday;

    private Double byTuesday;

    private Double byWednesday;

    private Double byThursday;

    private Double byFriday;

    private Double bySaturday;

    private Double bySunday;
}
