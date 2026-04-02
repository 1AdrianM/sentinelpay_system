package com.github.sentinel.pay.domain.entity.fraudRules;

import org.springframework.stereotype.Component;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

@Component
public record TransactionVelocityFraudRule() implements FraudRule {
  //  private static final int CRITICAL_INTERVAL_MS = 2000; // 2 segundos
   // private static final int SUSPICIOUS_INTERVAL_MS = 5000; // 5 segundos
   // private static final int WARNING_INTERVAL_MS = 30000;   // 30 segundos
   // private static final int MAX_TRANSACTIONS_PER_MINUTE = 10;
  
    @Override
    public FraudSignal evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
      if(accountRiskProfile.getVelocityProfile().isBursting()) {
        return FraudSignal.of(RiskMagnitude.HIGH, RiskImpactScale.SIGNIFICANT,"TransactionVelocityRule","Transaction too much quick");
      }
      return FraudSignal.of(RiskMagnitude.NEGLIGIBLE, RiskImpactScale.REDUCED, "TransactionVelocityRule", "Transaction in the estipulated time");
      }

}
