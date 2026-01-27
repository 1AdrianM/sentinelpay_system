package com.github.sentinel.pay.infrastructure.config.security;

import com.github.sentinel.pay.domain.entity.auth.user.UserPrincipal;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class SecurityUserDetails implements UserDetails {


    @Getter
    private final UserPrincipal principal;
    private final String password;

    public SecurityUserDetails(UserPrincipal principal, String password) {
        this.principal = principal;
        this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + principal.role().name())
        );
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        // Spring cree que esto es "username"
        // pero realmente es email
        return principal.email();
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

}
