package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudDecisionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudIncidentEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.TransactionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.FraudDecisionMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudDecisionMapperImpl implements FraudDecisionMapper {
    @Override
    public FraudDecisionEntity domainEntityToEntityModel(FraudDecision domainEntity) {

        return FraudDecisionEntity.builder()
                .id(domainEntity.getFraudDecisionId().id())
            .transaction(
                TransactionEntity
                .builder()
                .id(domainEntity.getTransactionId().id())
                .build())
                .fraudDecisionType(domainEntity.getFraudDecisionType())
                .accountId(domainEntity.getAccountId().id())
                .clientAccountId(domainEntity.getClientAccountId().id())
                .Description(domainEntity.getDescription())
                .issuedAt(domainEntity.getIssuedAt())
                .riskPoint(domainEntity.getRiskPoint().value())
                .modifiedAt(domainEntity.getModifiedAt())
                .build();
    }

    @Override
    public FraudDecision EntityModelToDomainEntity(FraudDecisionEntity entityModel) {
        return FraudDecision.builder()
                .fraudDecisionId(FraudDecisionId.of(entityModel.getId()))
                .transactionId(TransactionId.of(entityModel.getTransactionId()))
                .fraudDecisionType(entityModel.getFraudDecisionType())
                .accountId(AccountId.of(entityModel.getAccountId()))
                .clientAccountId(ClientAccountId.of(entityModel.getClientAccountId()))
                .description(entityModel.getDescription())
                .riskPoint(new RiskScore(entityModel.getRiskPoint()))
                .issuedAt(entityModel.getIssuedAt())
                .modifiedAt(entityModel.getModifiedAt())
                .build();
    }
}
