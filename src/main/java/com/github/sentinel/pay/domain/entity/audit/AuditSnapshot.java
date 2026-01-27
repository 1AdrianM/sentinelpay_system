package com.github.sentinel.pay.domain.entity.audit;

import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotKind;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotResourceType;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotVersion;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public  class AuditSnapshot {
    SnapshotResourceType resourceType;
    // DECISION / INCIDENT / TRANSACTION / RISK_ACCOUNT
    SnapshotKind kind;
    SnapshotVersion version;
    Instant capturedAt;
    public RiskScore riskScore;
    public BigDecimal amount;
    public String currency;
    public UUID accountId;
    public static AuditSnapshot fromDecision(SnapshotKind kind, RiskScore score){
        return new AuditSnapshot(SnapshotResourceType.DECISION,
                kind,
                new SnapshotVersion(0),
                Instant.now(),
                score,
                null,
                null,
                null);
    }
    public static AuditSnapshot fromIncident(SnapshotKind kind, RiskScore score){
   return new AuditSnapshot(SnapshotResourceType.INCIDENT,
                kind,
                new SnapshotVersion(0),
                Instant.now(),
                score,
                null,
                null,
                null);
    }

}
