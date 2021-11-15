package com.mafia.statistics.MafiaStatisticsAPI.security;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IPlayerDao playerDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nickname)
            throws UsernameNotFoundException {
        PlayerDto user = Optional.ofNullable(playerDao.findByNickname(nickname))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Player not found with nickname: " + nickname
                        )
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        PlayerDto user = playerDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Player", "id", id)
        );

        return UserPrincipal.create(user);
    }
}
