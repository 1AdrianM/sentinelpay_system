package com.github.sentinel.pay.domain.entity.FraudIncidentAggregate;

import java.time.Instant;

public class FraudIncident {
    private Long incidentId;
 private Long transactionId;
 private Long accountId;
 private Status status;
 private RiskScore riskScore;
 private Instant openedAt;
  private Instant resolvedAt;
}
