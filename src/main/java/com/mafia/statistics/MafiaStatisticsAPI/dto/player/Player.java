package com.mafia.statistics.MafiaStatisticsAPI.dto.player;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.CoupleStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RatingStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.RolesHistoryStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.SerialityStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.VisitingStatistics;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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

    private Long vkId;

    private String nickname;

    private String photoUrl;

    private Integer gamesTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private NumbersStatistics numbersStatistics;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<CoupleStatistics> coupleStatistics;

    @ManyToOne(cascade = CascadeType.ALL)
    private RatingStatistics ratingStatistics;

    @ManyToOne(cascade = CascadeType.ALL)
    private RolesHistoryStatistics rolesHistoryStatistics;

    @ManyToOne(cascade = CascadeType.ALL)
    private VisitingStatistics visitingStatistics;

    @ManyToOne(cascade = CascadeType.ALL)
    private SerialityStatistics serialityStatistics;
}
