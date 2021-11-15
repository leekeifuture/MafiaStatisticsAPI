package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SerialityStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private String nickname;

    private Integer gamesTotal;

    private Integer successivelyPlayedByRed; // with sheriff
    private Integer successivelyPlayedBySheriff;
    private Integer successivelyPlayedByBlack; // with don
    private Integer successivelyPlayedByDon;

    private Integer successivelyWonByRed; // with sheriff
    private Integer successivelyWonBySheriff;
    private Integer successivelyWonByBlack; // with don
    private Integer successivelyWonByDon;

    private Integer successivelyLostByRed; // with sheriff
    private Integer successivelyLostBySheriff;
    private Integer successivelyLostByBlack; // with don
    private Integer successivelyLostByDon;

    private Integer maximumSeriesOfWin;
    private Integer maximumSeriesOfDefeat;

    private Boolean isActive;
    private Date uploadingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        SerialityStatisticsAll that = (SerialityStatisticsAll) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
