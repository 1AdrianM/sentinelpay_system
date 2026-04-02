package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters.profile;

import org.springframework.stereotype.Component;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskProfileId;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.VelocityProfile;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.VelocityProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.VelocityProfileMapper;
@Component
public class VelocityProfileMapperImpl implements VelocityProfileMapper{

    @Override
    public VelocityProfileEntity domainEntityToEntityModel(VelocityProfile domainEntity) {
    
    
       return new VelocityProfileEntity(
        domainEntity.getId(),
       domainEntity.getAvgTxPerHour(),
       domainEntity.getAvgTxPerDay(),
       domainEntity.getCurrentHourCount(),
       domainEntity.getPeakTxPerHour(),
       domainEntity.getSamples(),
       domainEntity.getCurrentHourBucket(),
       domainEntity.getLastUpdated() );
       
    }

    @Override
    public VelocityProfile EntityModelToDomainEntity(VelocityProfileEntity entityModel) {
    return new VelocityProfile(
        entityModel.getId(),
       entityModel.getAvgTxPerHour(),
       entityModel.getAvgTxPerDay(),
       entityModel.getCurrentHourCount(),
       entityModel.getPeakTxPerHour(),
       entityModel.getSamples(),
       entityModel.getCurrentHourBucket(),
       entityModel.getLastUpdated()
    );
    }
    
}
