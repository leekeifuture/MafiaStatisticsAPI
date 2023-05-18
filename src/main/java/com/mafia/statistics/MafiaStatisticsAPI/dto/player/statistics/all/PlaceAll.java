package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class PlaceAll {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Integer gamesRed;
    @NonNull
    private Integer gamesBlack;
    @NonNull
    private Integer gamesDon;
    @NonNull
    private Integer gamesSheriff;

    @NonNull
    private Integer percentWinRed;
    @NonNull
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
