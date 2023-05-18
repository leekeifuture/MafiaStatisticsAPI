package com.mafia.statistics.MafiaStatisticsAPI.pyload.player;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.additional.Role;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.*;
import com.vk.api.sdk.objects.base.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @NonNull
    private Long id;

    @NonNull
    private String nickname;

    @NonNull
    private Long gamesTotal;

    @NonNull
    private Sex gender;

    private String photoUrl;

    private Set<Role> roles;

    private NumbersStatistics numbersStatistics;

    private List<CoupleStatistics> coupleStatistics;

    private RatingStatistics ratingStatistics;

    private RolesHistoryStatistics rolesHistoryStatistics;

    private VisitingStatistics visitingStatistics;

    private SerialityStatistics serialityStatistics;

    public Player(@NonNull Long id) {
        this.id = id;
    }
}
