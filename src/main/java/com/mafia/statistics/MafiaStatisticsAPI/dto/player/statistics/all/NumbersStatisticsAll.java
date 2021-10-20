package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.Place;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NumbersStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private String nickname;

    private Integer gamesTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeOne;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeTwo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeThree;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeFour;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeFive;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeSix;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeSeven;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeEight;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeNine;
    @ManyToOne(cascade = CascadeType.ALL)
    private Place placeTen;

    private Boolean isActive;
    private Date uploadingDate;
}
