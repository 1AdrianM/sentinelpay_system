package com.github.sentinel.pay.domain.entity.audit;

import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
public class AuditLog {
    private AuditLogId id;
    private UUID entityId;
    private AuditEntityType entityType;
    private AuditReason auditReason;
    private ClientAccountId  clientAccountId;
    private ActorType actorType; // apiKeyId o admin user
    private AuditAction action;
    private AuditSnapshot previousState;
    private AuditSnapshot newState;
    private Instant timestamp;

public static AuditLogId generateAuditLogId(){
    return new AuditLogId(UUID.randomUUID());
}
}
