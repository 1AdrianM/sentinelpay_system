package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class VelocityProfileEntity {
    @Id private UUID riskProfileId;
    private  BigDecimal avgTxPerHour;
    private BigDecimal avgTxPerDay;
    private int currentHourCount;
    private int peakTxPerHour;
    private long samples;
    private Instant currentHourBucket;
    private Instant lastUpdated;
}
