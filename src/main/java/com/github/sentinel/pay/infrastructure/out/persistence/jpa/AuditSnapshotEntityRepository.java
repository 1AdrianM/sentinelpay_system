package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditSnapshotEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import java.util.UUID;

public interface AuditSnapshotEntityRepository extends EntityRepository<AuditSnapshotEntity, UUID> {
}
