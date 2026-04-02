package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import groovy.transform.builder.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncidentStatisticsEntity {
    @Id private UUID id;
    private   List<String> status;
    private   Instant createdAt;

}
