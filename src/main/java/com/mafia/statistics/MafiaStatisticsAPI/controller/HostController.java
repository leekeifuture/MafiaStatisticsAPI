package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Games;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

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

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return hostService.createGame(game);
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
