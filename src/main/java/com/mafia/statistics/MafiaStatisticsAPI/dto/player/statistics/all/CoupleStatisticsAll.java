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
public class CoupleStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private Date fromDate;
    private Date toDate;

    private String nicknameOfMafiaOne;
    private String nicknameOfMafiaTwo;

    private Integer games;

    private Integer wins;

    private Float percentOfWins;

    private Integer number;

    private Boolean isActive;
    private Date uploadingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        CoupleStatisticsAll that = (CoupleStatisticsAll) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
