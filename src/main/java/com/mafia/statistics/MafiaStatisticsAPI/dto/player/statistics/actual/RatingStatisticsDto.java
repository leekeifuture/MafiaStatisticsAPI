package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

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
public class RatingStatisticsDto extends Statistics {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nickname;

    @NonNull
    private Date fromDate;
    @NonNull
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
    private Double bestMove;

    @NonNull
    private Double penaltyPoints; // best player / penalty

    @NonNull
    private Double points;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        RatingStatisticsDto that = (RatingStatisticsDto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
