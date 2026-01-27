package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record HighRiskAndRemoteTransactionFraudRule() implements FraudRule {
    @Override
    public RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
        if(tx.getTransactionType().isHighRisk() && tx.getTransactionType().isRemote()){
            return RiskContribution.MEDIUM;
        }
        return RiskContribution.NONE;
    }
}
