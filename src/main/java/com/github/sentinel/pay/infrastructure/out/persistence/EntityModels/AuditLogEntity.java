package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogEntity {
        @Id
        private UUID id;
        private UUID entityId;
        private UUID clientAccountId;
        private String entityType;
        private String auditReasonCode;
        private String auditReasonDescription;
        private String action;
        private String resourceId;
        private String actorType;
        private String previousState;
        private String  newState;
        private Instant timestamp;
}
