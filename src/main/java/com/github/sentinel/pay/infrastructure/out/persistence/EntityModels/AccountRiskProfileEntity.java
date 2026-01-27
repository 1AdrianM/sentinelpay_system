package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "risk_profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRiskProfileEntity {
    @Id private UUID riskProfileId;
    private UUID accountId;
    private UUID clientAccountId;
    // domain record transactionActivity equivalent;
      //domain record usualTransactionLocation;
      private String riskLevel;
    private Instant lastUpdated;
     private int averageRiskScore;

}
