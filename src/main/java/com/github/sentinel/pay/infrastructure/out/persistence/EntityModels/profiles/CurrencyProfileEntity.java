package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles;

import com.github.sentinel.pay.infrastructure.out.persistence.jpa.attributeConverter.CurrencyMapConverter;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyProfileEntity {
    @Id private UUID id;    
    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = CurrencyMapConverter.class)
    @Column(columnDefinition = "jsonb") // Postgres
    private Map<com.github.sentinel.pay.domain.entity.shared.Currency, Integer> currencyCount;
    private long samples;
    private Instant lastUpdated;

}
