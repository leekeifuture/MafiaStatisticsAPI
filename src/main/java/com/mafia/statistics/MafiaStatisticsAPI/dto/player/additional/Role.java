package com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
