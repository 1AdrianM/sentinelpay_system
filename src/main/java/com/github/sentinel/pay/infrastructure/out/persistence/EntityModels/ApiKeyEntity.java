package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "api_key")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiKeyEntity {
   @Id
   private UUID id;
    private String name;
    private UUID clientAccountId;
    private String hashedKey;
    private String status;
    private Instant createdAt;
    private Instant lastUsedAt;
}
