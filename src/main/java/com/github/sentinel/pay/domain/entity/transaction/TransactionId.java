package com.github.sentinel.pay.domain.entity.transaction;

import java.util.UUID;

public record TransactionId(
        UUID id
) {

    public static TransactionId of(UUID id) {
     return new TransactionId(id);
    }
}
