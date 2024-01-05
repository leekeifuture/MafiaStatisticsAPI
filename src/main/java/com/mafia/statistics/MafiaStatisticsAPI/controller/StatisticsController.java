package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.all.RatingStatisticsAll;
import com.mafia.statistics.MafiaStatisticsAPI.dto.statistics.DashboardInfo;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/statistics", produces = "application/json")
public class StatisticsController {

    private final IStatisticsService statisticsService;

    @GetMapping("/dashboard")
    public DashboardInfo getDashboardInfo() {
        return statisticsService.getDashboardInfo();
    }

    @GetMapping("/rating")
    public Map<String, List<RatingStatisticsAll>> getRatingByMonths(
            @RequestParam(defaultValue = "0") Integer minGames
    ) {
        return statisticsService.getRatingByMonths(minGames);
    }

    @GetMapping("/rating/{date}")
    public List<RatingStatisticsAll> getRatingByOneMonth(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM") Date date,
            @RequestParam(defaultValue = "0") Integer minGames
    ) {
        return statisticsService.getRatingByOneMonth(minGames, date);
    }
}
