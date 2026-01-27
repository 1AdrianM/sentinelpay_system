package com.github.sentinel.pay.domain.entity.fraudIncident;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;
@Getter
@AllArgsConstructor
@Builder
 public class FraudIncident {
 private FraudIncidentId incidentId;
 private ClientAccountId clientAccountId;
 private TransactionId transactionId;
 private FraudDecisionId fraudDecisionId;
 private AccountId accountId;
 private FraudIncidentStatus status;
 private RiskScore riskScore;
 private Instant openedAt;
  private Instant resolvedAt;

  public static FraudIncidentId generateIncidentId(){
   return new FraudIncidentId(UUID.randomUUID());
  }

    public static FraudIncident create(ClientAccountId clientAccountId, FraudIncidentId fraudIncidentId, TransactionId transactionId, FraudDecisionId decisionId, RiskScore riskPoint, AccountId accountId, FraudIncidentStatus status, Instant now) {
  return new FraudIncident(fraudIncidentId,clientAccountId,transactionId,decisionId,accountId,status,riskPoint,now,null);
  }

 public void resolvedAtNow() {
   this.resolvedAt = Instant.now();
  }

 public void changeIncidentStatus(FraudIncidentStatus fraudIncidentStatus) {
this.status =fraudIncidentStatus;
 }
}
