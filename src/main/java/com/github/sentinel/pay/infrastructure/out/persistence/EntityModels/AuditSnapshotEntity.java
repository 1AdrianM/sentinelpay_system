package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotKind;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotResourceType;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotVersion;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuditSnapshotEntity {
    @Id public UUID id;
    public UUID auditLogId;
    String resourceType;
     String kind;
     Instant capturedAt;
     public BigDecimal amount;
    public String currency;
    public UUID accountId;
    public int  version;
    public String status;
    public int score;
   }
