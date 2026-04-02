package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters.profile;

import org.springframework.stereotype.Component;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.MonetaryProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskProfileId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.MonetaryProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.MonetaryProfileMapper;
@Component
public class MonetaryProfileMapperImpl implements MonetaryProfileMapper {

    @Override
    public MonetaryProfileEntity domainEntityToEntityModel(MonetaryProfile domainEntity) {
     
      return new MonetaryProfileEntity
      (domainEntity.getId()
      ,domainEntity.getMean()
      ,domainEntity.getM2()
      ,domainEntity.getMaxAmountObserved()
      ,domainEntity.getMinAmountObserved()
      ,domainEntity.getSamples()
      ,domainEntity.getLastUpdated());
    }

    @Override
    public MonetaryProfile EntityModelToDomainEntity(MonetaryProfileEntity entityModel) {
        
    
        return new MonetaryProfile(entityModel.getId(),
        entityModel.getMean()
        ,entityModel.getM2()
        ,entityModel.getMaxAmountObserved()
        ,entityModel.getMinAmountObserved()
        ,entityModel.getSamples()
        ,entityModel.getLastUpdated());
    }
    
}
