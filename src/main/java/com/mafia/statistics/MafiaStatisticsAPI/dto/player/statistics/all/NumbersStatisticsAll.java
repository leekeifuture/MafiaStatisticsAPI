package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;

import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class NumbersStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private Date fromDate;
    private Date toDate;

    private Integer gamesTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeOne;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeTwo;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeThree;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeFour;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeFive;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeSix;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeSeven;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeEight;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeNine;
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeTen;

    private Boolean isActive;
    private Date uploadingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        NumbersStatisticsAll that = (NumbersStatisticsAll) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
