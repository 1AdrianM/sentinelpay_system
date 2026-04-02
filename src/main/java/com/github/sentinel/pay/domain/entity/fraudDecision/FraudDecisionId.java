package com.github.sentinel.pay.domain.entity.fraudDecision;

import java.util.List;
import java.util.UUID;

public record FraudDecisionId(
        UUID id
) {
public static List<FraudDecisionId> of(List<UUID> id) {
       return  id.stream().map(d-> new FraudDecisionId(d)).
       toList();
    }
    public static FraudDecisionId of(UUID id) {
        return new FraudDecisionId(id);
    }
}
