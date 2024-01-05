package com.mafia.statistics.MafiaStatisticsAPI.security;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        return UserPrincipal.create(user, getUserAttributes(user));
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        PlayerDto user = playerDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Player", "id", id)
        );

        return UserPrincipal.create(user, getUserAttributes(user));
    }

    private Map<String, Object> getUserAttributes(PlayerDto user) {
        Map<String, Object> attributes = new HashMap<>();

        if (user.getVkId() != null)
            attributes.put("vkId", user.getVkId());
        if (user.getGender() != null)
            attributes.put("gender", user.getGender());
        if (user.getFirstName() != null)
            attributes.put("firstName", user.getFirstName());
        if (user.getLastName() != null)
            attributes.put("lastName", user.getLastName());
        if (user.getPhotoUrl() != null)
            attributes.put("photoUrl", user.getPhotoUrl());

        return attributes;
    }
}
