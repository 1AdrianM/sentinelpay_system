package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudDecisionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.FraudDecisionMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudDecisionMapperImpl implements FraudDecisionMapper {
    @Override
    public FraudDecisionEntity domainEntityToEntityModel(FraudDecision domainEntity) {

        return FraudDecisionEntity.builder()
                .fraudDecisionId(domainEntity.getFraudDecisionId().id())
                .fraudDecisionType(domainEntity.getFraudDecisionType())
                .accountId(domainEntity.getAccountId().id())
                .Description(domainEntity.getDescription())
                .transactionId(domainEntity.getTransactionId().id())
                .issuedAt(domainEntity.getIssuedAt())
                .riskPoint(domainEntity.getRiskPoint().value())
                .modifiedAt(domainEntity.getModifiedAt())
                .build();
    }

    @Override
    public FraudDecision EntityModelToDomainEntity(FraudDecisionEntity entityModel) {
        return FraudDecision.builder()
                .fraudDecisionId(new FraudDecisionId(entityModel.getFraudDecisionId()))
                .fraudDecisionType(entityModel.getFraudDecisionType())
                .description(entityModel.getDescription())
                 .transactionId(new TransactionId(entityModel.getTransactionId()))
                .riskPoint(new RiskScore(entityModel.getRiskPoint()))
                .issuedAt(entityModel.getIssuedAt())
                .modifiedAt(entityModel.getModifiedAt())
                .build();
    }
}
