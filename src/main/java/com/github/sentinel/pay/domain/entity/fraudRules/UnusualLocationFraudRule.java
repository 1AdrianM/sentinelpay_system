package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record UnusualLocationFraudRule() implements FraudRule {
    @Override
    public FraudSignal evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
      if(accountRiskProfile.getLocationProfile().isUnusual(tx.getLocation())){
        return FraudSignal.of(RiskMagnitude.CRITICAL, RiskImpactScale.SIGNIFICANT,"UnusualLocationRule","Transaction Location is not the same as usual location");
        }
     return FraudSignal.of(RiskMagnitude.LOW,RiskImpactScale.MINIMAL,"UnusualLocation", "matches usual location, Rule was not triggered");
    }
}
