package com.github.sentinel.pay.infrastructure.out.persistence.adapter;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudDecisionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.base.BasePersistenceAdapter;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.FraudDecisionEntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.FraudDecisionMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FraudDecisionAdapter extends BasePersistenceAdapter<FraudDecision, FraudDecisionEntity, UUID> implements FraudDecisionRepository {
    private final FraudDecisionMapper entityMapper;
    private final FraudDecisionEntityRepository entityRepository;

    public FraudDecisionAdapter(FraudDecisionMapper entityMapper, FraudDecisionEntityRepository entityRepository) {
        super(entityMapper, entityRepository);
        this.entityMapper=entityMapper;
        this.entityRepository=entityRepository;
    }

    @Override
    public FraudDecision findByTransactionId(TransactionId transactionId) {
    return entityMapper.EntityModelToDomainEntity(this.entityRepository.findByTransactionId(transactionId.id()));
     }

    @Override
    public FraudDecision findByAccountId(AccountId accountId) {
        return entityMapper.EntityModelToDomainEntity(this.entityRepository.findByAccountId(accountId.id()));
    }

    @Override
    public List<FraudDecision> findLastTwoDecision(ClientAccountId clientAccountId) {
        var fraudDecision =this.entityRepository.findDecisions(clientAccountId.id(),PageRequest.of(0, 2));
        return fraudDecision.stream().map(entityMapper::EntityModelToDomainEntity).collect(Collectors.toList());


    }

    @Override
    public List<FraudDecision> findAllByClientAccountId(ClientAccountId clientAccountId, Pageable pageable) {
        return this.entityRepository
                .findDecisions(clientAccountId.id(),pageable)
                .stream()
                .map(entityMapper::EntityModelToDomainEntity)
                .collect(Collectors.toList());
    }
}
