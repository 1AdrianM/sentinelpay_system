package com.github.sentinel.pay.domain.entity.fraudIncident;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import org.springframework.stereotype.Component;

@Component
public class FraudIncidentStatusPolicy {
    public FraudIncidentStatus statusFromDecision(FraudDecisionType fraudDecisionType) {
        if(fraudDecisionType.equals(FraudDecisionType.APPROVED)) return FraudIncidentStatus.RESOLVED;
        if(fraudDecisionType.equals(FraudDecisionType.FRAUD_SUSPECTED)) return FraudIncidentStatus.OPEN;
        if(fraudDecisionType.equals(FraudDecisionType.REVIEW_REQUIRED)) return FraudIncidentStatus.UNDER_REVIEW;
        return  FraudIncidentStatus.OPEN;
     }
}
