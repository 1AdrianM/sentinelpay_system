package com.github.sentinel.pay.domain.services;

import com.github.sentinel.pay.domain.entity.AccountRiskProfileAggregate.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.AccountRiskProfileAggregate.RiskLevel;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudRules.FraudRule;
import com.github.sentinel.pay.domain.entity.fraudRules.RiskContribution;
import com.github.sentinel.pay.domain.entity.transactionAggregate.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class FraudEvaluationService {
    private final List<FraudRule> fraudRules;

    //Se toman la transaccion y se toma el perfil de riesgo, se analiza
// segun historial de perfil y reglas de fraude para la transaccion
FraudDecision evaluate(Transaction tx, AccountRiskProfile accountRiskProfile){
     int totalRiskScore =
             fraudRules.stream()
                     .map(fraudRule -> fraudRule.evaluateTransaction(tx, accountRiskProfile))
                     .mapToInt(RiskContribution::getScore).sum();

}
}
