package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record HighRiskAndRemoteTransactionFraudRule() implements FraudRule {
    @Override
    public FraudSignal evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
        if(tx.getTransactionType().isHighRisk() && tx.getTransactionType().isRemote()){
            return FraudSignal.of(RiskMagnitude.CRITICAL, RiskImpactScale.SIGNIFICANT, "HighRiskAndRemoteTransactionRule", "Transaction Type found to be High Risk and Also Remote");
        }
        return FraudSignal.of(RiskMagnitude.NEGLIGIBLE,RiskImpactScale.MINIMAL,"HighRiskAndRemoteTransactionRule", "Transaction Type found to be in the secure range");
    }
}
