package com.mafia.statistics.MafiaStatisticsAPI.dto.player;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RatingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.SerialityStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.VisitingStatistics;
import com.vk.api.sdk.objects.base.Sex;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String nickname;

    private Long gamesTotal;

    private Long vkId;
    private Sex gender;
    private String firstName;
    private String lastName;
    private String photoUrl;

    @OneToOne(cascade = CascadeType.ALL)
    private NumbersStatistics numbersStatistics;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<CoupleStatistics> coupleStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private RatingStatistics ratingStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private RolesHistoryStatistics rolesHistoryStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private VisitingStatistics visitingStatistics;

    @OneToOne(cascade = CascadeType.ALL)
    private SerialityStatistics serialityStatistics;

    public Player(Long id, String nickname, Sex gender, Long gamesTotal) {
        this.id = id;
        this.nickname = nickname;
        this.gender = gender;
        this.gamesTotal = gamesTotal;
    }
}
