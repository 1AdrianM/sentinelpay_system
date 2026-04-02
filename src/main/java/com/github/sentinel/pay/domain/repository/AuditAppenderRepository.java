package com.github.sentinel.pay.domain.repository;

import java.util.List;
import java.util.UUID;

import com.github.sentinel.pay.domain.entity.audit.ActorType;
import com.github.sentinel.pay.domain.entity.audit.AuditLog;
import com.github.sentinel.pay.domain.entity.shared.AccountId;

public interface AuditAppenderRepository {
    AuditLog append(AuditLog auditLog);
    List<AuditLog> getAuditsByEntityId(UUID entittyId);

}
