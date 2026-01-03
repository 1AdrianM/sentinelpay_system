package com.github.sentinel.pay.domain.entity.AccountRiskProfileAggregate;

import java.math.BigDecimal;
import java.time.Instant;

public class AccountRiskProfile {
    private Long accountId;
    private BigDecimal averageAmount;
    private Integer dailyTransactionCount;
    private RiskLevel riskLevel;
    private Instant lastUpdated;
}
