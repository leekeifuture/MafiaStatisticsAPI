package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

import org.hibernate.Hibernate;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class PlaceDto {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Long GamesRed;
    @NonNull
    private Long GamesBlack;
    @NonNull
    private Long GamesDon;
    @NonNull
    private Long GamesSheriff;

    @NonNull
    private Double PercentWinRed;
    @NonNull
    private Double PercentWinBlack;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        PlaceDto placeDto = (PlaceDto) o;
        return id != null && Objects.equals(id, placeDto.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
