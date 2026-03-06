package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

/**
 *
 */
public record TransactionAmountFraudRule() implements FraudRule {

    /**
     * @param tx
     * @param accountRiskProfile
     * @return
     */
    //
    @Override
    public FraudSignal evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
   if( accountRiskProfile.getMonetaryProfile().isAnomalous(tx.getMoney().amount())){
            return FraudSignal.of(RiskMagnitude.CRITICAL, RiskImpactScale.SIGNIFICANT,"TransactionAmountRule","Transaction Amount is thrice the usual amount ");
        }
        return  FraudSignal.of(RiskMagnitude.NEGLIGIBLE,RiskImpactScale.REDUCED,"TransactionAmountRule","Transaction amount is usual amount");
    }
}

