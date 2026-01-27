package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

 import com.github.sentinel.pay.domain.entity.audit.ActorType;
import com.github.sentinel.pay.domain.entity.audit.AuditAction;
import com.github.sentinel.pay.domain.entity.audit.AuditLog;
import com.github.sentinel.pay.domain.entity.audit.AuditLogId;
 import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AuditLogEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.AuditLogMapper;
 import org.springframework.stereotype.Component;

@Component
public class AuditLogMapperImpl implements AuditLogMapper {
    @Override
    public AuditLogEntity domainEntityToEntityModel(AuditLog domainEntity) {

        return AuditLogEntity.builder()
                .id(domainEntity.getId().id())
                 .actorType(domainEntity.getActorType().name())
                 .timestamp(domainEntity.getTimestamp())
                .clientAccountId(domainEntity.getId().id())
                .action(domainEntity.getAction().name())
                .actorType(domainEntity.getActorType().name())
                 .build();
    }

    @Override
    public AuditLog EntityModelToDomainEntity(AuditLogEntity entityModel) {
        return AuditLog.builder()
                 .id(new AuditLogId(entityModel.getId()))
                 .timestamp(entityModel.getTimestamp())
                .action(AuditAction.valueOf(entityModel.getAction()))
                .actorType(ActorType.valueOf(entityModel.getActorType()))
                 .clientAccountId(new ClientAccountId(entityModel.getId()))
                 .build();
    }
}
