package com.mafia.statistics.MafiaStatisticsAPI.security;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserPrincipal implements OAuth2User, UserDetails {

    @NonNull
    private Long id;

    @NonNull
    private String nickname;

    @NonNull
    private Collection<? extends GrantedAuthority> authorities;

    private Map<String, Object> attributes;

    public static UserPrincipal create(Player player) {
        return new UserPrincipal(
                player.getId(),
                player.getNickname(),
                player.getRoles()
        );
    }

    public static UserPrincipal create(Player player, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(player);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public String getPassword() {
        return nickname;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
