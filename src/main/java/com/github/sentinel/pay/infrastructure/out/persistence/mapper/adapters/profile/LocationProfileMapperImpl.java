package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters.profile;


import org.springframework.stereotype.Component;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.LocationProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskProfileId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.LocationProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.LocationProfileMapper;
 
@Component
public class LocationProfileMapperImpl implements LocationProfileMapper{
    @Override
    public LocationProfileEntity domainEntityToEntityModel(LocationProfile domainEntity) {
        
        return new LocationProfileEntity(
            domainEntity.getId(),
            domainEntity.getLocationCount(),
            domainEntity.getSamples(),
            domainEntity.getLastUpdatedAt());

    }

    @Override
    public LocationProfile EntityModelToDomainEntity(LocationProfileEntity entityModel) {
     
        return new LocationProfile(
            entityModel.getId(),
            entityModel.getLocationCount(),
            entityModel.getSamples(),
            entityModel.getLastUpdated());

  }
}
