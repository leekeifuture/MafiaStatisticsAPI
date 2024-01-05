package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.base.Statistics;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class SerialityStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

    @NonNull
    private String nickname;

    @NonNull
    private Integer gamesTotal;

    @NonNull
    private Integer successivelyPlayedByRed; // with sheriff
    @NonNull
    private Integer successivelyPlayedBySheriff;
    @NonNull
    private Integer successivelyPlayedByBlack; // with don
    @NonNull
    private Integer successivelyPlayedByDon;

    @NonNull
    private Integer successivelyWonByRed; // with sheriff
    @NonNull
    private Integer successivelyWonBySheriff;
    @NonNull
    private Integer successivelyWonByBlack; // with don
    @NonNull
    private Integer successivelyWonByDon;

    @NonNull
    private Integer successivelyLostByRed; // with sheriff
    @NonNull
    private Integer successivelyLostBySheriff;
    @NonNull
    private Integer successivelyLostByBlack; // with don
    @NonNull
    private Integer successivelyLostByDon;

    @NonNull
    private Integer maximumSeriesOfWin;
    @NonNull
    private Integer maximumSeriesOfDefeat;

    @NonNull
    private Boolean isActive;
    @NonNull
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
