package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;
@Entity
@Table(name = "fraud_decisions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudDecisionEntity {
   @Id private UUID fraudDecisionId;
    private UUID transactionId;
 private UUID clientAccountId;
 private int riskPoint;
    private FraudDecisionType fraudDecisionType;
    private UUID accountId;
    private String Description;
    private Instant issuedAt;
    private Instant modifiedAt;

}
