package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import java.util.UUID;

public record RiskProfileId(
        UUID id
) {
    public static RiskProfileId of(UUID riskProfileId) {
            return new RiskProfileId(riskProfileId);
    }
}
