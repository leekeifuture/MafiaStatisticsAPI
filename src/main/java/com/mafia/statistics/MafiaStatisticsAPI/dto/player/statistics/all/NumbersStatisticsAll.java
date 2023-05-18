package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class NumbersStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nickname;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

    @NonNull
    private Integer gamesTotal;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeOne;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeTwo;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeThree;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeFour;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeFive;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeSix;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeSeven;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeEight;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeNine;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private PlaceAll placeTen;

    @NonNull
    private Boolean isActive;
    @NonNull
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
