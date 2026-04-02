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
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VelocityProfileEntity {
    @Id private UUID id;
    private  BigDecimal avgTxPerHour;
    private BigDecimal avgTxPerDay;
    private int currentHourCount;
    private int peakTxPerHour;
    private long samples;
    private Instant currentHourBucket;
    private Instant lastUpdated;


}
