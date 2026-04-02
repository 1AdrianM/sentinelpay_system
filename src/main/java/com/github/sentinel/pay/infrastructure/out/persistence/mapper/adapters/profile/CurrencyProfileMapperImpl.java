package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters.profile;


import org.springframework.stereotype.Component;


import com.github.sentinel.pay.domain.entity.accountRiskProfile.CurrencyProfile;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.profiles.CurrencyProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.CurrencyProfileMapper;

@Component
public class CurrencyProfileMapperImpl implements CurrencyProfileMapper {

    @Override
    public CurrencyProfileEntity domainEntityToEntityModel(CurrencyProfile domainEntity) {
    
        return new CurrencyProfileEntity(
          domainEntity.getId(), 
        domainEntity.getCurrencyCount(),
        domainEntity.getSample(),
        domainEntity.getLastUpdated());

}

    @Override
    public CurrencyProfile EntityModelToDomainEntity(CurrencyProfileEntity entityModel) {
                return new CurrencyProfile(
                 entityModel.getId(),
                 entityModel.getCurrencyCount(),
                 entityModel.getSamples(),
                 entityModel.getLastUpdated());
        
    }
    
}
