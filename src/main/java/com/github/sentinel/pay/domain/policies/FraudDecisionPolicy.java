package com.github.sentinel.pay.domain.policies;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import org.springframework.stereotype.Component;

@Component
public class FraudDecisionPolicy {

  public FraudDecisionType decide(RiskScore riskScore){
      if(riskScore.isRestrictedScore()) return FraudDecisionType.FRAUD_SUSPECTED;
      if(riskScore.isHighRisk()) return FraudDecisionType.FRAUD_SUSPECTED;
     if (riskScore.isMediumRisk()) return FraudDecisionType.REVIEW_REQUIRED;
     if (riskScore.isLowRisk()) return FraudDecisionType.REVIEW_REQUIRED;
     return FraudDecisionType.APPROVED;
  }
}
