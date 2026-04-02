package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudDecisionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudIncidentEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.TransactionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.FraudIncidentMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudIncidentMapperImpl implements FraudIncidentMapper {
    @Override
    public FraudIncidentEntity domainEntityToEntityModel(FraudIncident domainEntity) {
        return FraudIncidentEntity.builder()
                .id(domainEntity.getIncidentId().id())
                .transaction(
                    TransactionEntity
                    .builder()
                    .id(domainEntity.getTransactionId().id())
                    .build()
                )
                .clientAccountId(domainEntity.getClientAccountId().id())
                .accountId(domainEntity.getAccountId().id())
                .decision( 
                     FraudDecisionEntity.builder()
                    .id(domainEntity.getFraudDecisionId().id()).build()
                )
                .status(domainEntity.getStatus())
                .riskScore(domainEntity.getRiskScore().value())
                .openedAt(domainEntity.getOpenedAt())
                .lastUpdatedAt(domainEntity.getLastUpdatedAt())
                .resolvedAt(domainEntity.getResolvedAt())
                .build();
    }

    @Override
    public FraudIncident EntityModelToDomainEntity(FraudIncidentEntity entityModel) {
        return FraudIncident.builder()
                .incidentId( FraudIncidentId.of(entityModel.getId()))
                .clientAccountId(
                    ClientAccountId
                    .of(entityModel.getClientAccountId()))
                .fraudDecisionId( 
                    FraudDecisionId
                    .of(entityModel
                    .getFraudDecisionId()))
                .transactionId(
                    TransactionId
                    .of(entityModel.getTransactionId()))
                .accountId(AccountId.of(entityModel.getAccountId()))
                .status(entityModel.getStatus())
                .riskScore(RiskScore.of(entityModel.getRiskScore()))
                .openedAt(entityModel.getOpenedAt())
                .lastUpdatedAt(entityModel.getLastUpdatedAt())
                .resolvedAt(entityModel.getResolvedAt())
                .build();
    }
}
