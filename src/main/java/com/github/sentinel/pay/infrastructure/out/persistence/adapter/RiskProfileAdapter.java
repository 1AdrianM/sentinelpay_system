package com.github.sentinel.pay.infrastructure.out.persistence.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.*;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.shared.Location;
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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RiskProfileAdapter extends BasePersistenceAdapter<AccountRiskProfile, AccountRiskProfileEntity, UUID> implements RiskProfileRepository {
   private final RiskProfileMapper entityMapper;
   private final AccountRiskProfileEntityRepository entityRepository;
   private final MonetaryEntityRepository monetaryEntityRepository;
   private final LocationEntityRepository locationEntityRepository;
   private final CurrencyEntityRepository currencyEntityRepository;
   private final VelocityEntityRepository velocityEntityRepository;
   private final ObjectMapper objectMapper;

    public RiskProfileAdapter(RiskProfileMapper entityMapper,
                              AccountRiskProfileEntityRepository entityRepository,
                              MonetaryEntityRepository monetaryEntityRepository,
                              LocationEntityRepository locationEntityRepository,
                              CurrencyEntityRepository currencyEntityRepository,
                              VelocityEntityRepository velocityEntityRepository, ObjectMapper objectMapper) {
        super(entityMapper, entityRepository);
        this.entityRepository=entityRepository;
        this.entityMapper=entityMapper;
        this.monetaryEntityRepository = monetaryEntityRepository;
        this.locationEntityRepository = locationEntityRepository;
        this.currencyEntityRepository =currencyEntityRepository;
        this.velocityEntityRepository= velocityEntityRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<AccountRiskProfile> findAllOrderByRiskScoreDesc() {
        var entities= entityRepository.findAllByOrderByAverageRiskScoreDesc();
        return entities.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());
    }
// TODO: move hydration to application service

    @Override
    public AccountRiskProfile findOrCreateByAccountId(AccountId accountId) {
       var entity= entityRepository.findByAccountId(accountId.id()).orElseGet(()-> {
           var riskProfile = AccountRiskProfile.initial(
                   ClientAccountId.generateRiskProfileId(),
                   AccountRiskProfile.generateRiskProfileId(),
                   accountId);
                  var risk= save(riskProfile);
                  return entityMapper.domainEntityToEntityModel(risk);
       });
      var domainEntity =entityMapper.EntityModelToDomainEntity(entity);
      var riskProfileId = new RiskProfileId(entity.getRiskProfileId());
      var currencyProfile=  findCurrencyProfileByRiskProfileId(riskProfileId);
     var monetaryProfile=  findMonetaryProfileByRiskProfileId(riskProfileId);
     var locationProfile=    findLocationProfileByRiskProfileId(riskProfileId);
     var velocityProfile=   findVelocityProfileByRiskProfileId(riskProfileId);
      domainEntity.hidrateProfile(locationProfile,monetaryProfile,velocityProfile,currencyProfile);
      return domainEntity;
    }

    @Override
    public AccountRiskProfile update(AccountRiskProfile accountRiskProfile) {
     return update(accountRiskProfile.getRiskProfileId().id(), accountRiskProfile);

    }

    @Override
    public CurrencyProfile findCurrencyProfileByRiskProfileId(RiskProfileId id) {
        var entity= this.currencyEntityRepository.findById(id.id()).orElseThrow(()-> new RuntimeException(""));
     var map=  fromJsonCurrency(entity.getCurrencyCountJson());
        return new CurrencyProfile(map,entity.getSample());
    }

    @Override
    public VelocityProfile findVelocityProfileByRiskProfileId(RiskProfileId id) {
       var entity= this.velocityEntityRepository.findById(id.id()).orElseThrow(()-> new RuntimeException(""));;
        return new VelocityProfile(entity.getAvgTxPerHour(), entity.getAvgTxPerDay(), entity.getCurrentHourCount(),entity.getPeakTxPerHour(),entity.getSamples(),entity.getCurrentHourBucket(),entity.getLastUpdated());
    }

    @Override
    public MonetaryProfile findMonetaryProfileByRiskProfileId(RiskProfileId id) {
        var entity= this.monetaryEntityRepository.findById(id.id()).orElseThrow(()-> new RuntimeException(""));;
        return new MonetaryProfile(entity.getMean(),entity.getM2(),entity.getMaxAmountObserved(),entity.getMinAmountObserved(),entity.getSamples());
    }

    @Override
    public LocationProfile findLocationProfileByRiskProfileId(RiskProfileId id) {
        var entity= this.locationEntityRepository.findById(id.id()).orElseThrow(()-> new RuntimeException(""));;
     var map = fromJsonLocation(entity.getLocationCountsJson());
        return new LocationProfile(map,entity.getSamples());
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
    public AccountRiskProfile findByAccountId(AccountId accountId) {
        AccountRiskProfileEntity accountRiskProfileEntity= this.entityRepository.findByAccountId(accountId.id()).orElseThrow(()-> new RuntimeException("risk profile with said account is not found"));
        return this.entityMapper.EntityModelToDomainEntity(accountRiskProfileEntity);
    }

    private Map<Currency, Integer> fromJsonCurrency(String json) {
        try {
            return objectMapper.readValue(
                    json,
                    new TypeReference<Map<Currency, Integer>>() {}
            );
        } catch (Exception e) {
            throw new IllegalStateException("Failed to deserialize location counts", e);
        }
    }
    private Map<Location, Integer> fromJsonLocation(String json) {
        try {
            return objectMapper.readValue(
                    json,
                    new TypeReference<Map<Location, Integer>>() {}
            );
        } catch (Exception e) {
            throw new IllegalStateException("Failed to deserialize location counts", e);
        }
    }
}
