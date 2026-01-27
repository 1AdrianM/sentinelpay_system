package com.github.sentinel.pay.infrastructure.config.security.provider;

import com.github.sentinel.pay.application.services.AuthService;
import com.github.sentinel.pay.domain.entity.auth.user.UserPrincipal;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class DomainAuthenticationProvider implements AuthenticationProvider {
    private final AuthService authService;

    public DomainAuthenticationProvider(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        // TU dominio decide
        UserPrincipal principal =
                authService.authenticate(email, password);

        return new UsernamePasswordAuthenticationToken(
                principal,
                null,
                List.of(new SimpleGrantedAuthority(
                        "ROLE_" + principal.role().name()
                )));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
