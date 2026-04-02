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
     @Id private UUID id;
    @Column(nullable = false)
     private UUID clientAccountId;
     @Column(nullable = false)
     private UUID accountId;
     @Enumerated(value = EnumType.STRING)
     private FraudIncidentStatus status;
     private int     riskScore;
     private Instant openedAt;
     private Instant lastUpdatedAt;

     private Instant resolvedAt;


     @Column(name = "decision_id", insertable = false, updatable = false)
     private UUID fraudDecisionId;
     @ManyToOne()
     @JoinColumn(name = "decision_id")
     private FraudDecisionEntity decision;

     @Column(name = "transaction_id", insertable = false, updatable = false)
     private UUID transactionId;

     @ManyToOne()
     @JoinColumn(name = "transaction_id")
     private TransactionEntity transaction;

}
