package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Games;
import com.mafia.statistics.MafiaStatisticsAPI.security.CurrentUser;
import com.mafia.statistics.MafiaStatisticsAPI.security.UserPrincipal;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/host/game", produces = "application/json")
public class HostController {

    private final IHostService hostService;

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return hostService.getGameById(id);
    }

    @GetMapping
    public Games getAllGames(
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return hostService.getAllGames(limit, page);
    }

    @PreAuthorize("hasAuthority('HOST') || hasAuthority('ADMIN')")
    @PostMapping
    public Game createGame(
            @RequestBody Game game,
            @CurrentUser UserPrincipal userPrincipal
    ) {
        return hostService.createGame(game, userPrincipal);
    }

    @PatchMapping("/{id}")
    public Game updateGame(@PathVariable Long id, @RequestBody Game game) {
        return hostService.updateGame(id, game);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        hostService.deleteGame(id);
        return ResponseEntity.ok().build();
    }
}
