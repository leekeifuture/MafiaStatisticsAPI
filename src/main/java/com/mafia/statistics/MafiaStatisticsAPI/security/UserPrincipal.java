package com.mafia.statistics.MafiaStatisticsAPI.security;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserPrincipal implements OAuth2User, UserDetails {

    private Long id;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(
            Long id, String nickname,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.nickname = nickname;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Player player) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new UserPrincipal(
                player.getId(),
                player.getNickname(),
                authorities
        );
    }

    public static UserPrincipal create(Player player, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(player);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId() {
        return id;
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

    private void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
