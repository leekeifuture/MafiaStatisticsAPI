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
public class VisitingStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private Date fromDate;
    private Date toDate;

    private Integer byMonday;

    private Integer byTuesday;

    private Integer byWednesday;

    private Integer byThursday;

    private Integer byFriday;

    private Integer bySaturday;

    private Integer bySunday;

    private Boolean isActive;
    private Date uploadingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        VisitingStatisticsAll that = (VisitingStatisticsAll) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
