package com.github.sentinel.pay.domain.entity.fraudDecision;

import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter

@AllArgsConstructor
@Builder
 public class FraudDecision {
    private FraudDecisionId fraudDecisionId;
    private ClientAccountId clientAccountId;
    private TransactionId transactionId;
    private RiskScore riskPoint;
    private FraudDecisionType fraudDecisionType;
    private AccountId accountId;
    private String description;
    private Instant issuedAt;
    private Instant modifiedAt;
    public static FraudDecisionId generatefraudDecisionId(){
        return new FraudDecisionId(UUID.randomUUID());
    }
    public void updateDecision(FraudDecisionType fraudDecisionType, String description, Instant modifiedAt){
     if(fraudDecisionType!=null)this.fraudDecisionType= fraudDecisionType;
     if (!description.isEmpty()|| !description.isBlank()) this.description = description;
     if(modifiedAt !=null)this.modifiedAt = modifiedAt;
    }
    public static FraudDecision create(FraudDecisionId fraudDecisionId,ClientAccountId clientAccountId, AccountId accountId, Instant now, TransactionId transactionId, FraudDecisionType decisionType,RiskScore score) {
return new FraudDecision(fraudDecisionId, clientAccountId,transactionId,score,decisionType,accountId,"",now,now);
    }
}
