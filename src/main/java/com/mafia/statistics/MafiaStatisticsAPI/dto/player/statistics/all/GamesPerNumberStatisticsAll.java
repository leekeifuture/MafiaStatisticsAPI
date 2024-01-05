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
public class GamesPerNumberStatisticsAll extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Integer number;

    @NonNull
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date fromDate;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM", timezone = "Europe/Minsk")
    private Date toDate;

    @NonNull
    private Integer gamesTotal;

    @NonNull
    private Integer firstShot;

    @NonNull
    private Integer percentFirstShot;

    @NonNull
    private Integer percentSelectedRed;
    @NonNull
    private Integer percentSelectedBlack;
    @NonNull
    private Integer percentSelectedDon;
    @NonNull
    private Integer percentSelectedSheriff;

    @NonNull
    private Integer percentWinningRed;
    @NonNull
    private Integer percentWinningBlack;
    @NonNull
    private Integer percentWinningDon;
    @NonNull
    private Integer percentWinningSheriff;

    @NonNull
    private Integer percentWinningAllRed; // with sheriff
    @NonNull
    private Integer percentWinningAllBlack; // with don

    @NonNull
    private Boolean isActive;
    @NonNull
    private Date uploadingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        GamesPerNumberStatisticsAll that = (GamesPerNumberStatisticsAll) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
