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
public class CoupleStatisticsAll extends Statistics {

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
    private String nicknameOfMafiaOne;
    @NonNull
    private String nicknameOfMafiaTwo;

    @NonNull
    private Integer games;

    @NonNull
    private Integer wins;

    @NonNull
    private Float percentOfWins;

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
        CoupleStatisticsAll that = (CoupleStatisticsAll) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
