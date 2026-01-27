package com.github.sentinel.pay.application.dto.decision;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class FraudDecisionDto {
    String decisionId;
    String accountId;
    String decisionType;
    Instant createdAt;
    String description;
}
