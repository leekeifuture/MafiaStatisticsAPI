package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Place {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Long GamesRed;
    @NonNull
    private Long GamesBlack;
    @NonNull
    private Long GamesDon;
    @NonNull
    private Long GamesSheriff;

    @NonNull
    private Double PercentWinRed;
    @NonNull
    private Double PercentWinBlack;
}
