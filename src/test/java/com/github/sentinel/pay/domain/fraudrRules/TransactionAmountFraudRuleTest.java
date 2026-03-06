package com.github.sentinel.pay.domain.fraudrRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.CurrencyProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.IncidentStatistics;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.LocationProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.MonetaryProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskProfileId;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.VelocityProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.fraudRules.TransactionAmountFraudRule;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.Channel;
import com.github.sentinel.pay.domain.entity.transaction.Money;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionType;
import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionAmountFraudRuleTest {
    @Test
    void  shouldReturnHighRiskWhenTransactionAmountExceedsAverage(){
         Transaction tx = new Transaction(
                new TransactionId(java.util.UUID.randomUUID()),
                new ClientAccountId(java.util.UUID.randomUUID()),
                new AccountId(java.util.UUID.randomUUID()),
                new FraudIncidentId(java.util.UUID.randomUUID()),
                TransactionType.ONLINE_PAYMENT,
                new Location("RD","SD"),
                new Money(new BigDecimal("6090"), Currency.USD),
                Instant.now(),
                Channel.ONLINE
         );
         AccountRiskProfile riskProfile = new AccountRiskProfile(
                AccountRiskProfile.generateRiskProfileId(),
                new ClientAccountId(java.util.UUID.randomUUID()),
                new AccountId(java.util.UUID.randomUUID()),
                RiskLevel.LOW,
                null, // IncidentStatistics
                LocationProfile.empty(),
                MonetaryProfile.initial(new BigDecimal("2000")),
                CurrencyProfile.empty(),
                VelocityProfile.initial(),
                1, // averageRiskScore
                Instant.now()
         );
         TransactionAmountFraudRule rule = new TransactionAmountFraudRule();
      var result= rule.evaluateTransaction(tx,riskProfile);
        FraudSignal expectedSignal = FraudSignal.of(RiskMagnitude.CRITICAL, RiskImpactScale.SIGNIFICANT, "TransactionAmountFraudRule", "Transaction amount exceeds average and indicates high risk.");
        assertEquals(expectedSignal.score(), result.score());
        assertEquals(expectedSignal.weight(), result.weight());
        assertEquals(expectedSignal.ruleTriggered(), result.ruleTriggered());
        assertEquals(expectedSignal.description(), result.description());
    }
}
