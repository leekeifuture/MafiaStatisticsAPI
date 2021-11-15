package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import org.hibernate.Hibernate;

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
public class PlaceAll {

    @Id
    @GeneratedValue
    private Long id;

    private Integer gamesRed;
    private Integer gamesBlack;
    private Integer gamesDon;
    private Integer gamesSheriff;

    private Integer percentWinRed;
    private Integer percentWinBlack;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        PlaceAll placeAll = (PlaceAll) o;
        return id != null && Objects.equals(id, placeAll.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
