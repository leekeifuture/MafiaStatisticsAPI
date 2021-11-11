package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.security.CurrentUser;
import com.mafia.statistics.MafiaStatisticsAPI.security.UserPrincipal;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/players", produces = "application/json")
public class PlayerController {

    private final IPlayerService playerService;

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/me")
    public Player getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return playerService.getPlayerById(userPrincipal.getId());
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }
}
