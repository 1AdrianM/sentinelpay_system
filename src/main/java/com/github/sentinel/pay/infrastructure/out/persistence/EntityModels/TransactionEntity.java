package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.transaction.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id private UUID transactionId;
    private UUID accountId;
    private UUID fraudIncidentId;
    private UUID clientAccountId;
    private TransactionType transactionType;
     private BigDecimal amount;
    private String currency;
    private Instant timestamp;
   private String city;
    private String country;
     private String channel;
     @Column(name = "confirmed_fraud")
     private boolean confirmedFraud;
}
