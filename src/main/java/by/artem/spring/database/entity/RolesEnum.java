package by.artem.spring.database.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RolesEnum implements GrantedAuthority {
    ADMIN, COACH, ATHLETE;
    @Override
    public String getAuthority() {
        return name();
    }
}
