package com.github.sentinel.pay.domain.policies;

import com.github.sentinel.pay.domain.entity.audit.AuditReason;
import com.github.sentinel.pay.domain.entity.audit.reasonCode.DecisionAuditReasons;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import org.springframework.stereotype.Component;

@Component
public class AuditReasonPolicy {
    public AuditReason forgeFromDecisionType(FraudDecisionType fraudDecisionType) {
      //Si la razon ha sido un rule match
        if(fraudDecisionType.equals(FraudDecisionType.APPROVED)) return new AuditReason(DecisionAuditReasons.AUTO_APPROVAL,"Automatic Aproval due to no signal of frauds");
        if(fraudDecisionType.equals(FraudDecisionType.FRAUD_SUSPECTED)) return new AuditReason(DecisionAuditReasons.AUTO_REJECTION,"Automatic Rejection due to High Risk Score Detection");
        if(fraudDecisionType.equals(FraudDecisionType.REVIEW_REQUIRED)) return new AuditReason(DecisionAuditReasons.MANUAL_REVIEW,"Alarms were fired, needs a review");
       return new AuditReason(null,null);
    }
    //TODO
    public AuditReason forgeFromIncidentStatus(FraudIncidentStatus status){
        return null;
    }
    public AuditReason forgeFromTransactionEvaluation(){
        return null;
    }
}
