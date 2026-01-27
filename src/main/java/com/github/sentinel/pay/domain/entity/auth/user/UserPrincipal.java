package com.github.sentinel.pay.domain.entity.auth.user;

import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;

import java.util.UUID;

public record UserPrincipal(
        UUID userId,
        ClientAccountId clientAccountId,
        String email,
        UserRole role

) {}