package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class MonetaryProfileEntity {
    @Id private UUID riskProfileId;
    private BigDecimal mean;
     private  BigDecimal m2;
     private  BigDecimal maxAmountObserved;
     private  BigDecimal minAmountObserved;
      private long samples;
    private Instant lastUpdated;

}
