package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.AccountRiskProfileAggregate.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.FraudIncidentAggregate.RiskScore;
import com.github.sentinel.pay.domain.entity.transactionAggregate.Transaction;

public interface FraudRule {
   RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile);
}
