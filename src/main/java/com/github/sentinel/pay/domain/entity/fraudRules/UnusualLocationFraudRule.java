package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record UnusualLocationFraudRule() implements FraudRule {
    @Override
    public RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
      if(accountRiskProfile.getLocationProfile().isUnusual(tx.getLocation())){
        return RiskContribution.HIGH;
        }
     return RiskContribution.NONE;
    }
}
