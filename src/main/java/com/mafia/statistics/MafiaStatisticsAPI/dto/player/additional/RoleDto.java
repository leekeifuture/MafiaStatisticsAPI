package com.mafia.statistics.MafiaStatisticsAPI.dto.player.additional;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

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
