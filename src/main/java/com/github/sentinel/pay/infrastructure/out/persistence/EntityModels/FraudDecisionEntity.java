package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fraud_decisions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FraudDecisionEntity {
    @Id private UUID id;
    @Column(nullable = false)
    private UUID clientAccountId;
    private int riskPoint;
    private FraudDecisionType fraudDecisionType;
    @Column(nullable = false)
    private UUID accountId;
    private String Description;
    private Instant issuedAt;
    private Instant modifiedAt;

    @OneToMany(mappedBy = "decision")
    private List<FraudIncidentEntity> incidents;

    @Column(name = "transaction_id", insertable = false, updatable = false)
     private UUID transactionId;
    @ManyToOne()
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

}
