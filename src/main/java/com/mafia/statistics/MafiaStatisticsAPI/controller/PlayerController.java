package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.security.CurrentUser;
import com.mafia.statistics.MafiaStatisticsAPI.security.UserPrincipal;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final IPlayerDao playerDao;

    private final IPlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = playerService.getPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public Player getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return playerDao.findById(userPrincipal.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player", "id", userPrincipal.getId())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
