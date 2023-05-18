package com.mafia.statistics.MafiaStatisticsAPI.controller;

import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.IPlayerMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.security.CurrentUser;
import com.mafia.statistics.MafiaStatisticsAPI.security.UserPrincipal;
import com.mafia.statistics.MafiaStatisticsAPI.service.inter.IPlayerService;
import com.vk.api.sdk.objects.base.Sex;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/player", produces = "application/json")
public class PlayerController {

    private final IPlayerService playerService;

    private final IPlayerMapper playerMapper;

    @GetMapping("/all")
    public List<Player> getPlayers() {
        return playerService.getPlayers().stream()
                .map(playerMapper::dtoToPlayerMin)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/me")
    public Player getCurrentUser(
            @CurrentUser UserPrincipal userPrincipal,
            @RequestParam(defaultValue = "false") Boolean isMinInfo
    ) {
        return isMinInfo
                ? playerMapper.dtoToPlayerMin(playerService.getPlayerById(userPrincipal.getId()))
                : playerMapper.dtoToPlayer(playerService.getPlayerById(userPrincipal.getId()));
    }

    @GetMapping("/{id}")
    public Player getPlayerById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") Boolean isMinInfo
    ) {
        return isMinInfo
                ? playerMapper.dtoToPlayerMin(playerService.getPlayerById(id))
                : playerMapper.dtoToPlayer(playerService.getPlayerById(id));
    }

    @GetMapping("/search")
    public List<Player> searchPlayersByNickname(@RequestParam String query) {
        return playerService.searchPlayersByNickname(query);
    }

    @PreAuthorize("hasAuthority('HOST') || hasAuthority('ADMIN')")
    @PostMapping
    public Player createPlayer(
            @RequestParam String nickname,
            @RequestParam(defaultValue = "UNKNOWN") Sex gender
    ) {
        return playerMapper.dtoToPlayerMin(
                playerService.savePlayer(nickname, gender, false)
        );
    }
}
