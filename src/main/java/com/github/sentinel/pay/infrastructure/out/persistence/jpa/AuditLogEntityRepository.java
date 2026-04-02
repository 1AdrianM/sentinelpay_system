package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.domain.entity.audit.AuditLog;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditLogEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.Entity;

import java.util.List;
import java.util.UUID;

public interface AuditLogEntityRepository extends EntityRepository<AuditLogEntity, UUID> {
    @Query("""
        SELECT a 
        From AuditLogEntity a
         where 
         entityId =:entityId
        """)
    List<AuditLogEntity> findAuditsByEntityId(UUID entityId);
}
