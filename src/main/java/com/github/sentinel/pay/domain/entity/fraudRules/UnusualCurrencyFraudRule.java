package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record UnusualCurrencyFraudRule() implements FraudRule {
    @Override
    public FraudSignal evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
     if ( accountRiskProfile.getCurrencyProfile().isUnusualCurrency(tx.getMoney().currency())){
            return FraudSignal.of(RiskMagnitude.HIGH, RiskImpactScale.MODERATE,"UnusualCurrencyRule", "Transaction didint meet the usual Currency criteria");
        }
        return FraudSignal.of(RiskMagnitude.LOW,RiskImpactScale.MINIMAL,"UnusualCurrencyRule", " Rule was not triggered");

    }
}
