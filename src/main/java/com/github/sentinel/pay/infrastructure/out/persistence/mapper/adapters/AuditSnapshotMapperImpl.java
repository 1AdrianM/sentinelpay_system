package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.audit.AuditLogId;
import com.github.sentinel.pay.domain.entity.audit.AuditSnapshot;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditSnapshotEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.AuditSnapshotMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class AuditSnapshotMapperImpl implements AuditSnapshotMapper {
    @Override
    public AuditSnapshotEntity domainEntityToEntityModel(AuditSnapshot domainEntity, AuditLogId auditLogId) {
        return AuditSnapshotEntity.builder()
                .id(UUID.randomUUID())
                .auditLogId(auditLogId.id())
                .score(domainEntity.riskScore.value())
                .capturedAt(domainEntity.getCapturedAt())
                .version(domainEntity.getVersion().value)
                .amount(domainEntity.getAmount())
                .accountId(domainEntity.getAccountId())
                .currency(domainEntity.getCurrency())
                .resourceType(domainEntity.getResourceType().name())
                .kind(domainEntity.getKind().name())
                  .build();
    }

    @Override
    public AuditSnapshotEntity domainEntityToEntityModel(AuditSnapshot domainEntity) {
        return null;
    }

    @Override
    public AuditSnapshot EntityModelToDomainEntity(AuditSnapshotEntity entityModel) {
        return null;
    }
}
