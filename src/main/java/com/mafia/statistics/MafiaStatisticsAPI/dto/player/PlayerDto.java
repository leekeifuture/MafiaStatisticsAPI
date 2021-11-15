package com.mafia.statistics.MafiaStatisticsAPI.dto.player;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional.RoleDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatisticsDto;
import com.vk.api.sdk.objects.base.Sex;

import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class PlayerDto {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nickname;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<RoleDto> roles;

    private Long gamesTotal;

    @Column(unique = true)
    private Long vkId;
    private Sex gender;
    private String firstName;
    private String lastName;
    private String photoUrl;

    @OneToOne(cascade = CascadeType.ALL)
    private NumbersStatisticsDto numbersStatistics;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    private List<CoupleStatisticsDto> coupleStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private RatingStatisticsDto ratingStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private RolesHistoryStatisticsDto rolesHistoryStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private VisitingStatisticsDto visitingStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private SerialityStatisticsDto serialityStatistics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        PlayerDto playerDto = (PlayerDto) o;
        return id != null && Objects.equals(id, playerDto.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
