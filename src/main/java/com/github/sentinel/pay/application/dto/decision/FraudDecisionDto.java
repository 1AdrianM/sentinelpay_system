package com.github.sentinel.pay.application.dto.decision;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class FraudDecisionDto {
    public String decisionId;
    public String accountId;
    public String status;
   public String decisionType;
   public String actor;
   //public String riskLevel;
   public Instant createdAt;
   public Instant modifiedAt;
   public String description;
}
