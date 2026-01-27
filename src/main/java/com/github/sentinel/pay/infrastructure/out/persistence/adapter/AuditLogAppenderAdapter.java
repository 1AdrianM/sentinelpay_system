package com.github.sentinel.pay.infrastructure.out.persistence.adapter;

import com.github.sentinel.pay.domain.entity.audit.AuditLog;
import com.github.sentinel.pay.domain.repository.AuditAppenderRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditLogEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.base.BasePersistenceAdapter;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.AuditLogEntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.AuditSnapshotEntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.AuditLogMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.AuditSnapshotMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Component
public class AuditLogAppenderAdapter extends BasePersistenceAdapter<AuditLog, AuditLogEntity, UUID> implements AuditAppenderRepository {
    private final AuditSnapshotEntityRepository auditSnapshotEntityRepository;
    private final AuditSnapshotMapper auditSnapshotMapper;

    public AuditLogAppenderAdapter(AuditLogMapper entityMapper, AuditLogEntityRepository entityRepository, AuditSnapshotEntityRepository auditSnapshotEntityRepository, AuditSnapshotMapper auditSnapshotMapper) {
        super(entityMapper, entityRepository);
        this.auditSnapshotEntityRepository = auditSnapshotEntityRepository;
        this.auditSnapshotMapper = auditSnapshotMapper;
    }

    @Override
    public AuditLog append(AuditLog auditLog) {

          var savedLog=  save(auditLog);

        if (auditLog.getPreviousState()!=null){
        var auditSnapshot= auditSnapshotMapper.domainEntityToEntityModel(auditLog.getPreviousState(), auditLog.getId());
      auditSnapshotEntityRepository.save(auditSnapshot);
    }

        if (auditLog.getNewState()!=null){
            var auditSnapshot= auditSnapshotMapper.domainEntityToEntityModel(auditLog.getNewState(), auditLog.getId());
            auditSnapshotEntityRepository.save(auditSnapshot);
         }
        return savedLog;
    }
}
