package com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional;

import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class RoleDto implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        RoleDto roleDto = (RoleDto) o;
        return id != null && Objects.equals(id, roleDto.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
