package com.github.sentinel.pay.infrastructure.out.persistence.adapter;


import com.github.sentinel.pay.domain.entity.accountRiskProfile.*;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.base.BasePersistenceAdapter;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.*;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.RiskProfileMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RiskProfileAdapter extends BasePersistenceAdapter<AccountRiskProfile, AccountRiskProfileEntity, UUID> implements RiskProfileRepository {
   private final RiskProfileMapper entityMapper;
   private final AccountRiskProfileEntityRepository entityRepository;

    public RiskProfileAdapter(RiskProfileMapper entityMapper,
                              AccountRiskProfileEntityRepository entityRepository,
                              MonetaryEntityRepository monetaryEntityRepository,
                              LocationEntityRepository locationEntityRepository,
                              CurrencyEntityRepository currencyEntityRepository,
                              VelocityEntityRepository velocityEntityRepository) {
        super(entityMapper, entityRepository);
        this.entityRepository=entityRepository;
        this.entityMapper=entityMapper;
    }

    @Override
    public List<AccountRiskProfile> findAllOrderByRiskScoreDesc() {
        var entities= entityRepository.findAllByOrderByAverageRiskScoreDesc();
        return entities.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }


    @Override
    public AccountRiskProfile update(AccountRiskProfile accountRiskProfile) {
     return update(accountRiskProfile.getRiskProfileId().id(), accountRiskProfile);

    }

    @Override
    public int findByHighAndRestrictedAccountCount(ClientAccountId clientAccountId) {
        return this.entityRepository.findByHighAndRestrictedAccountCount(clientAccountId.id());
    }

    @Override
    public List<AccountRiskProfile> findLastFiveRiskProfileAccounts(ClientAccountId clientAccountId) {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("lastUpdated").descending());
        var riskProfileList=  this.entityRepository.findLastRiskProfiles(clientAccountId.id(),pageable);
        return riskProfileList.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountRiskProfile> findByAccountId(AccountId accountId) {
   return this.entityRepository.findByAccountId(accountId.id()).map(entityMapper::EntityModelToDomainEntity);
      
    }

   
}
