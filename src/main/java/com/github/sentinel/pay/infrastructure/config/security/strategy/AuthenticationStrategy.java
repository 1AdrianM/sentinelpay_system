package com.github.sentinel.pay.infrastructure.config.security.strategy;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationStrategy {
    void authenticate(HttpServletRequest request);

}
