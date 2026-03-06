package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public interface FraudRule {
   FraudSignal evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile);
}
