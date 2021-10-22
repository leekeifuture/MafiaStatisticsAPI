package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base;

import java.util.Date;

import lombok.Data;

@Data
public class Statistics {

    private Date fromDate;
    private Date toDate;

    private Boolean isActive;
}
