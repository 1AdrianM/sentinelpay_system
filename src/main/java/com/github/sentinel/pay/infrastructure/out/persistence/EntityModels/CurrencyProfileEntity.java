package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.shared.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Data
public class CurrencyProfileEntity {
    @Id
    private UUID riskProfileId;

    @Column(columnDefinition = "jsonb") // Postgres
   private String currencyCountJson;
   private long sample;
    private Instant lastUpdated;

}
