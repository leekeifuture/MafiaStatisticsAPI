package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.NumbersStatistics;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/statistics", produces = "application/json")
public class StatisticsController {

    @GetMapping("/numbers")
    public ResponseEntity<List<NumbersStatistics>> getNumbersStatistics() {
        List<NumbersStatistics> numbersStatistics = null; // TODO: null
        return new ResponseEntity<>(numbersStatistics, HttpStatus.OK);
    }
}
