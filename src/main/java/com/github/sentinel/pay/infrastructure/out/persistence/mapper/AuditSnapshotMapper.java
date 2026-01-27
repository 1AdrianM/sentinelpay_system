package com.github.sentinel.pay.infrastructure.out.persistence.mapper;

import com.github.sentinel.pay.domain.entity.audit.AuditLogId;
import com.github.sentinel.pay.domain.entity.audit.AuditSnapshot;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditSnapshotEntity;

public interface AuditSnapshotMapper extends EntityMapper<AuditSnapshot, AuditSnapshotEntity>{
    public AuditSnapshotEntity domainEntityToEntityModel(AuditSnapshot domainEntity, AuditLogId auditLogId);
}
