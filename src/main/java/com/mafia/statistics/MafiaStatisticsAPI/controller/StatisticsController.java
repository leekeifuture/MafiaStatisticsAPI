package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.DashboardInfo;
import com.mafia.statistics.MafiaStatisticsAPI.exception.PlayerNotFoundException;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IStatisticsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/statistics", produces = "application/json")
public class StatisticsController {

    private final IStatisticsService statisticsService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardInfo> getDashboardInfo() throws PlayerNotFoundException {
        DashboardInfo dashboardInfo = statisticsService.getDashboardInfo();
        return new ResponseEntity<>(dashboardInfo, HttpStatus.OK);
    }
}
