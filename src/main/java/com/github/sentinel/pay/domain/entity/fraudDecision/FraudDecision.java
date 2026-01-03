package com.github.sentinel.pay.domain.entity.fraudDecision;

import java.time.Instant;

public class FraudDecision {
    private Long decisionId;
    private Long transactionId;
    private Long accountId;
    private String Description;
    private Instant issuedAt;
}
