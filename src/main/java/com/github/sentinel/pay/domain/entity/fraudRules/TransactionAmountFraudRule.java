package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
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
    public RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
   if( accountRiskProfile.getMonetaryProfile().isAnomalous(tx.getMoney().amount())){
            return RiskContribution.HIGH;
        }
        return  RiskContribution.NONE;
    }
}

