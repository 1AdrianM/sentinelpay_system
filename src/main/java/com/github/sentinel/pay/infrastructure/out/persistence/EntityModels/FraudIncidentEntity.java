package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "fraud_incidents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudIncidentEntity {
    @Id
     private UUID incidentId;
     private UUID transactionId;
    private UUID fraudDecisionId;
   private UUID clientAccountId;
     private UUID accountId;
     @Enumerated(value = EnumType.STRING)
     private FraudIncidentStatus status;
     private int  riskScore;
     private Instant openedAt;
     private Instant resolvedAt;
}
