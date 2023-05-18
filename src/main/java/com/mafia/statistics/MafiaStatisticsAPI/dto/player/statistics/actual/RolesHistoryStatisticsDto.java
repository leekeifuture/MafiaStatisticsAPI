package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

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
public class RolesHistoryStatisticsDto extends Statistics {

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
    private Long gamesTotal;

    @NonNull
    private Long gamesRed;
    @NonNull
    private Long gamesBlack;
    @NonNull
    private Long gamesDon;
    @NonNull
    private Long gamesSheriff;

    @NonNull
    private Long shooting;

    @NonNull
    private Double percentSelectedRed;
    @NonNull
    private Double percentSelectedBlack;
    @NonNull
    private Double percentSelectedDon;
    @NonNull
    private Double percentSelectedSheriff;

    @NonNull
    private Double percentSelectedAllRed; // with sheriff
    @NonNull
    private Double percentSelectedAllBlack; // with don

    @NonNull
    private Double percentWinningRed;
    @NonNull
    private Double percentWinningBlack;
    @NonNull
    private Double percentWinningDon;
    @NonNull
    private Double percentWinningSheriff;

    @NonNull
    private Double percentWinning;

    @NonNull
    private Double percentWinningAllRed; // with sheriff
    @NonNull
    private Double percentWinningAllBlack; // with don

    @NonNull
    private Double percentBestPlayer;

    @NonNull
    private Double percentFirstShooting;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        RolesHistoryStatisticsDto that = (RolesHistoryStatisticsDto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
