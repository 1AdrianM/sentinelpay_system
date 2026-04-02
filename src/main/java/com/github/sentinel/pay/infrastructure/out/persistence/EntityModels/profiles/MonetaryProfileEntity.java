package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;

import groovy.transform.builder.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonetaryProfileEntity {
    @Id private UUID id; 

    private BigDecimal mean;
     private  BigDecimal m2;
     private  BigDecimal maxAmountObserved;
     private  BigDecimal minAmountObserved;
      private long samples;
    private Instant lastUpdated;


}
