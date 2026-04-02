package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.transaction.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id private UUID id;
    @Column(nullable = false)
    private UUID accountId; //relationship
    @Column(nullable = false)
    private UUID clientAccountId;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

     private BigDecimal amount;
    private String currency;
    private Instant timestamp;
   private String city;
    private String country;
     private String channel;
     @Column(name = "confirmed_fraud")
     private boolean confirmedFraud;

     @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY)
     private List<FraudIncidentEntity> fraudIncidents;

     @OneToMany(mappedBy="transaction", fetch = FetchType.LAZY)
     private List<FraudDecisionEntity> fraudDecisions;

}
