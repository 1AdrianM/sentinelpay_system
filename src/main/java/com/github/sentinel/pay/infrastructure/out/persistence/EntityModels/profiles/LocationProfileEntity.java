package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles;

import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.attributeConverter.LocationMapConverter;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@NoArgsConstructor
@AllArgsConstructor
public class LocationProfileEntity {
     @Id private UUID id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = LocationMapConverter.class)
    @Column(columnDefinition = "jsonb") // Postgres
    private Map<Location, Integer> locationCount;
    private long samples;
    private Instant lastUpdated;

}
