package com.github.sentinel.pay.infrastructure.config.security.filter;

import com.github.sentinel.pay.application.exceptions.UnauthorizedException;
import com.github.sentinel.pay.infrastructure.config.security.AuthResolver;
import com.github.sentinel.pay.infrastructure.config.security.strategy.AuthenticationStrategy;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final AuthResolver authResolver;

    public AuthenticationFilter(AuthResolver authResolver) {
        this.authResolver = authResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            AuthenticationStrategy authenticator =
                    authResolver.resolve(request);

            if (authenticator != null) {
                authenticator.authenticate(request);
            }

            filterChain.doFilter(request, response);

        } finally {
            TenantContextHolder.clear(); // 🔥 SIEMPRE
        }
    }
}
