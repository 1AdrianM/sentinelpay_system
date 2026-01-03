package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.AccountRiskProfileAggregate.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.FraudIncidentAggregate.RiskScore;
import com.github.sentinel.pay.domain.entity.fraudRules.FraudRule;
import com.github.sentinel.pay.domain.entity.transactionAggregate.Transaction;

public record AccessOutsideOfOpeningHoursFraudRule() implements FraudRule {


    @Override
    public RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
        return null;
    }
}
