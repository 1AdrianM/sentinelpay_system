package com.github.sentinel.pay.infrastructure.config.security;

import com.github.sentinel.pay.infrastructure.config.security.strategy.ApiKeyAuthenticator;
import com.github.sentinel.pay.infrastructure.config.security.strategy.AuthenticationStrategy;
import com.github.sentinel.pay.infrastructure.config.security.strategy.SessionAuthenticator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthResolver {
    private final ApiKeyAuthenticator apiKeyAuthenticator;
    private final SessionAuthenticator sessionAuthenticator;

    public AuthResolver(ApiKeyAuthenticator apiKeyAuthenticator, SessionAuthenticator sessionAuthenticator) {
        this.apiKeyAuthenticator = apiKeyAuthenticator;
        this.sessionAuthenticator = sessionAuthenticator;
    }

    public AuthenticationStrategy resolve(HttpServletRequest request) {

        if (request.getHeader("X-API-KEY") != null) {
            return apiKeyAuthenticator;
        }

        if (request.getUserPrincipal() != null) {
            return sessionAuthenticator;
        }

        return null; // public route
    }
}
