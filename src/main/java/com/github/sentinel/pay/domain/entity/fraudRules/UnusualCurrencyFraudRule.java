package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record UnusualCurrencyFraudRule() implements FraudRule {
    @Override
    public RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
     if ( accountRiskProfile.getCurrencyProfile().isUnusualCurrency(tx.getMoney().currency())){
            return RiskContribution.MEDIUM;
        }
        return RiskContribution.NONE;
    }
}
