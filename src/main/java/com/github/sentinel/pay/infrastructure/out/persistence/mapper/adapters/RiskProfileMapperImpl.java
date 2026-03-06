package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskProfileId;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.RiskProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class RiskProfileMapperImpl implements RiskProfileMapper {
    @Override
    public AccountRiskProfileEntity domainEntityToEntityModel(AccountRiskProfile domainEntity) {
        return AccountRiskProfileEntity.builder()
                .riskProfileId(domainEntity.getRiskProfileId().id())
                  .accountId(domainEntity.getAccountId().id())
                .riskLevel(domainEntity.getRiskLevel().name())
                 .lastUpdated(domainEntity.getLastUpdated())
                .build();
                    }

    @Override
    public AccountRiskProfile EntityModelToDomainEntity(AccountRiskProfileEntity entityModel) {
        return AccountRiskProfile.builder()
                .riskProfileId(RiskProfileId.of(entityModel.getRiskProfileId()))
                .accountId(new AccountId(entityModel.getAccountId()))
                .riskLevel(RiskLevel.valueOf(entityModel.getRiskLevel()))
                .lastUpdated(entityModel.getLastUpdated())
                .build();

    }
}
