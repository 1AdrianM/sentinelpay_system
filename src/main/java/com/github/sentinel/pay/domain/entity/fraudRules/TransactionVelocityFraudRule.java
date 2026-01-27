package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record TransactionVelocityFraudRule() implements FraudRule {
    private static final int CRITICAL_INTERVAL_MS = 2000; // 2 segundos
    private static final int SUSPICIOUS_INTERVAL_MS = 5000; // 5 segundos
    private static final int WARNING_INTERVAL_MS = 30000;   // 30 segundos
    private static final int MAX_TRANSACTIONS_PER_MINUTE = 10;


    /**
     * @param tx
     * @param accountRiskProfile
     * @return
     */
    @Override
    public RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
      if(accountRiskProfile.getVelocityProfile().isBursting()) {
        return RiskContribution.HIGH;
      }
      return RiskContribution.NONE;
      }

}
