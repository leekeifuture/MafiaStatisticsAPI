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
public class VisitingStatisticsDto extends Statistics {

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
    private Double byMonday;

    @NonNull
    private Double byTuesday;

    @NonNull
    private Double byWednesday;

    @NonNull
    private Double byThursday;

    @NonNull
    private Double byFriday;

    @NonNull
    private Double bySaturday;

    @NonNull
    private Double bySunday;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        VisitingStatisticsDto that = (VisitingStatisticsDto) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
