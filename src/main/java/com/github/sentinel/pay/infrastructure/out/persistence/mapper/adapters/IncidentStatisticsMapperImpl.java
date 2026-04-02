package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.IncidentStatistics;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.IncidentStatisticsEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.IncidentStatisticsMapper;
@Component
public class IncidentStatisticsMapperImpl implements IncidentStatisticsMapper {

    @Override
    public IncidentStatisticsEntity domainEntityToEntityModel(IncidentStatistics domainEntity) {
          List<String> statusList = domainEntity.getStatus().stream().map(d->d.name()).toList();


        return new IncidentStatisticsEntity(
                 domainEntity.getId(),
                 statusList,
                 domainEntity.getCreatedAt()
        );
    }

    @Override
    public IncidentStatistics EntityModelToDomainEntity(IncidentStatisticsEntity entityModel) {
List<FraudIncidentStatus> statusList = entityModel.getStatus().stream().map(s-> FraudIncidentStatus.valueOf(s)).toList();


return new IncidentStatistics(
                    entityModel.getId(),
                    statusList,
                    entityModel.getCreatedAt());
    }
    
}
