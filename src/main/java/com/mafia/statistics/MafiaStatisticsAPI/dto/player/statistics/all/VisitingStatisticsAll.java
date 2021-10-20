package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class VisitingStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private String nickname;

    private Integer byMonday;

    private Integer byTuesday;

    private Integer byWednesday;

    private Integer byThursday;

    private Integer byFriday;

    private Integer bySaturday;

    private Integer bySunday;

    private Boolean isActive;
    private Date uploadingDate;
}
