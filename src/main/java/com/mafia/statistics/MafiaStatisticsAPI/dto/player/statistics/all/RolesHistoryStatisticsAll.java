package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RolesHistoryStatisticsAll extends Statistics {

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
    private Integer gamesRed;
    @NonNull
    private Integer gamesBlack;
    @NonNull
    private Integer gamesDon;
    @NonNull
    private Integer gamesSheriff;

    @NonNull
    private Integer shooting;

    @NonNull
    private Integer percentSelectedRed;
    @NonNull
    private Integer percentSelectedBlack;
    @NonNull
    private Integer percentSelectedDon;
    @NonNull
    private Integer percentSelectedSheriff;

    @NonNull
    private Integer percentSelectedAllRed; // with sheriff
    @NonNull
    private Integer percentSelectedAllBlack; // with don

    @NonNull
    private Integer percentWinningRed;
    @NonNull
    private Integer percentWinningBlack;
    @NonNull
    private Integer percentWinningDon;
    @NonNull
    private Integer percentWinningSheriff;

    @NonNull
    private Integer percentWinning;

    @NonNull
    private Integer percentWinningAllRed; // with sheriff
    @NonNull
    private Integer percentWinningAllBlack; // with don

    @NonNull
    private Integer percentBestPlayer;

    @NonNull
    private Integer percentFirstShooting;

    @NonNull
    private Integer number;

    @NonNull
    private Boolean isActive;
    @NonNull
    private Date uploadingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        RolesHistoryStatisticsAll that = (RolesHistoryStatisticsAll) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
