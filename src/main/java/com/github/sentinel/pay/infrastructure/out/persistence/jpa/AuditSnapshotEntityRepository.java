package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditLogEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditSnapshotEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

public interface AuditSnapshotEntityRepository extends EntityRepository<AuditSnapshotEntity, UUID> {

}
