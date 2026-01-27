package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudIncidentEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.FraudIncidentMapper;
import org.springframework.stereotype.Component;

@Component
public class FraudIncidentMapperImpl implements FraudIncidentMapper {
    @Override
    public FraudIncidentEntity domainEntityToEntityModel(FraudIncident domainEntity) {
        return FraudIncidentEntity.builder()
                .incidentId(domainEntity.getIncidentId().id())
                .fraudDecisionId(domainEntity.getFraudDecisionId().id())
                .status(domainEntity.getStatus())
                .transactionId(domainEntity.getTransactionId().id())
                .riskScore(domainEntity.getRiskScore().value())
                .openedAt(domainEntity.getOpenedAt())
                .resolvedAt(domainEntity.getResolvedAt())
                .build();
    }

    @Override
    public FraudIncident EntityModelToDomainEntity(FraudIncidentEntity entityModel) {
        return FraudIncident.builder()
                .incidentId(new FraudIncidentId(entityModel.getIncidentId()))
                .fraudDecisionId(new FraudDecisionId(entityModel.getFraudDecisionId()))
                .transactionId(new TransactionId(entityModel.getTransactionId()))
                .accountId(new AccountId(entityModel.getAccountId()))
                .status(entityModel.getStatus())
                .riskScore(new RiskScore(entityModel.getRiskScore()))
                .openedAt(entityModel.getOpenedAt())
                .resolvedAt(entityModel.getResolvedAt())
                .build();
    }
}
