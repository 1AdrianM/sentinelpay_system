package com.github.sentinel.pay.domain.entity.shared;

import java.util.UUID;

public record ClientAccountId(UUID id) {
    public static ClientAccountId generateRiskProfileId(){
        return new ClientAccountId(UUID.randomUUID());
    }

}
