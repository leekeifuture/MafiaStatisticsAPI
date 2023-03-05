package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IHostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/host/game", produces = "application/json")
public class HostController {

    private final IHostService hostService;

    @GetMapping("/{id}")
    public Object getGameById(@PathVariable Long id) {
        return hostService.getGameById(id);
    }

    @GetMapping
    public List<Game> getAllGames() {
        return hostService.getAllGames();
    }

    @PostMapping("/create")
    public Game createGame() {
        return hostService.createGame();
    }

    @PutMapping("/update")
    public Game updateGame() {
        return hostService.updateGame();
    }
}
