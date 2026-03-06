package com.github.sentinel.pay.domain.fraudrRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.*;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.AverageCurrencyTransaction;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.fraudRules.UnusualLocationFraudRule;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.TransactionActivity;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class UnusualLocationFraudRuleTest {

    @Test
    void ShouldReturnHighRiskContributionWhenTransactionLocationIsUnusual(){
        Transaction tx = new Transaction(
                new TransactionId(java.util.UUID.randomUUID()),
                new ClientAccountId(java.util.UUID.randomUUID()),
                new AccountId(java.util.UUID.randomUUID()),
                new FraudIncidentId(java.util.UUID.randomUUID()),
                TransactionType.CRYPTO_TRANSFER,
                new Location("RD","SD"),
                new Money(new BigDecimal("6000"), Currency.USD),
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
        UnusualLocationFraudRule  rule = new UnusualLocationFraudRule();
       var result= rule.evaluateTransaction(tx,riskProfile);
        Assertions.assertEquals(FraudSignal.of(RiskMagnitude.CRITICAL, RiskImpactScale.SIGNIFICANT,"UnusualLocationRule","Transaction Location is not the same as usual location"), result);
    }
}
