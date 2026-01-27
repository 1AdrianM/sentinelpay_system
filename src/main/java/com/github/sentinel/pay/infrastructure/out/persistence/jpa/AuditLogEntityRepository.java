package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.domain.entity.audit.AuditLog;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditLogEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import org.w3c.dom.Entity;

import java.util.UUID;

public interface AuditLogEntityRepository extends EntityRepository<AuditLogEntity, UUID> {
}
