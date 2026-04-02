package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskProfileId;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.CurrencyProfileMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.IncidentStatisticsMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.LocationProfileMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.MonetaryProfileMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.RiskProfileMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.VelocityProfileMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiskProfileMapperImpl implements RiskProfileMapper {
     private final LocationProfileMapper locationProfileMapper;
     private final MonetaryProfileMapper monetaryProfileMapper;
     private final VelocityProfileMapper velocityProfileMapper;
    private final CurrencyProfileMapper currencyProfileMapper;
    private final IncidentStatisticsMapper incidentStatisticsMapper;
    
    @Override
    public AccountRiskProfileEntity domainEntityToEntityModel(AccountRiskProfile domainEntity) {
        return AccountRiskProfileEntity.builder()
                .id(domainEntity.getRiskProfileId().id())
                .clientAccountId(domainEntity.getClientAccountId().id())
                .accountId(domainEntity.getAccountId().id())
                .riskLevel(domainEntity.getRiskLevel().name())
                .lastUpdated(domainEntity.getLastUpdated())
                .riskScoreSamples(domainEntity.getRiskScoreSamples())
                .IncidentStatistics(incidentStatisticsMapper.domainEntityToEntityModel(domainEntity.getIncidents()))
                .currencyProfileEntity(currencyProfileMapper.domainEntityToEntityModel(domainEntity.getCurrencyProfile()))
                .locationProfileEntity(locationProfileMapper.domainEntityToEntityModel(domainEntity.getLocationProfile()))
                .monetaryProfileEntity(monetaryProfileMapper.domainEntityToEntityModel(domainEntity.getMonetaryProfile()))
                .velocityProfileEntity(velocityProfileMapper.domainEntityToEntityModel(domainEntity.getVelocityProfile()))
                .build();
                    }

    @Override
    public AccountRiskProfile EntityModelToDomainEntity(AccountRiskProfileEntity entityModel) {
        return AccountRiskProfile.builder()
                .riskProfileId(RiskProfileId.of(entityModel.getId()))
                .accountId(AccountId.of(entityModel.getAccountId()))
                .clientAccountId(ClientAccountId.of(entityModel.getClientAccountId()))
                .riskLevel(RiskLevel.valueOf(entityModel.getRiskLevel()))
                .lastUpdated(entityModel.getLastUpdated())
                .riskScoreSamples(entityModel.getRiskScoreSamples())
                .incidents(incidentStatisticsMapper.EntityModelToDomainEntity(entityModel.getIncidentStatistics()))
                .locationProfile(locationProfileMapper.EntityModelToDomainEntity(entityModel.getLocationProfileEntity()))
                .monetaryProfile(monetaryProfileMapper.EntityModelToDomainEntity(entityModel.getMonetaryProfileEntity()))
                .velocityProfile(velocityProfileMapper.EntityModelToDomainEntity(entityModel.getVelocityProfileEntity()))
                .currencyProfile(currencyProfileMapper.EntityModelToDomainEntity(entityModel.getCurrencyProfileEntity()))
                .build();

    }
}
