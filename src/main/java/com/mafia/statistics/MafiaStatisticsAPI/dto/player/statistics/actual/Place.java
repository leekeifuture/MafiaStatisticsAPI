package com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Place {

    @Id
    @GeneratedValue
    private Long id;

    private Integer gamesRed;
    private Integer gamesBlack;
    private Integer gamesDon;
    private Integer gamesSheriff;

    private Integer percentWinRed;
    private Integer percentWinBlack;
}
