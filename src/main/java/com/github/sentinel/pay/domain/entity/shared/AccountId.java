package com.github.sentinel.pay.domain.entity.shared;

import java.util.UUID;

public record AccountId(
        UUID id
) {

    public static AccountId of(UUID accountId) {
        return new AccountId(accountId);
    }
}
