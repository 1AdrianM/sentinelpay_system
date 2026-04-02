package com.github.sentinel.pay.domain.fraudrRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.AverageCurrencyTransaction;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.TransactionActivity;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.LocationProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.MonetaryProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.CurrencyProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.VelocityProfile;
import com.github.sentinel.pay.domain.entity.fraudRules.TransactionVelocityFraudRule;
import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

public class VelocityFraudRuleTest {

    @Test
     void ShouldReturnHighRiskContributionWhenTooManyTransactionAreMadeInATimeFrame(){
      Instant instant = Instant.now();
        Deque<Instant> lastHourTx = new ArrayDeque<>();
        lastHourTx.add(instant.plus(Duration.ofSeconds(3)));
        lastHourTx.add(instant.plus(Duration.ofSeconds(15)));
        lastHourTx.add(instant.plus(Duration.ofSeconds(120)));

                var profileId= AccountRiskProfile.generateRiskProfileId();

        AccountRiskProfile riskProfile = new AccountRiskProfile(
                profileId,
                new ClientAccountId(java.util.UUID.randomUUID()),
                new AccountId(java.util.UUID.randomUUID()),
                RiskLevel.LOW,
                null, // IncidentStatistics
                LocationProfile.initial(),
                MonetaryProfile.initial(new BigDecimal("2000")),
                CurrencyProfile.initial(),
                VelocityProfile.initial(),
                1, // averageRiskScore
                Instant.now()
        );
        Transaction tx =  Transaction.create(
                new TransactionId(java.util.UUID.randomUUID()),
                new ClientAccountId(java.util.UUID.randomUUID()),
                new AccountId(java.util.UUID.randomUUID()),
                new Location("RD","SD"),
                new Money(new BigDecimal("6000"), Currency.USD),
                TransactionType.CRYPTO_TRANSFER,
                Channel.ONLINE,
               lastHourTx.getLast().plus(Duration.ofMinutes(2))

        );

        TransactionVelocityFraudRule rule = new TransactionVelocityFraudRule();
        var result =rule.evaluateTransaction(tx,riskProfile);
        Assertions.assertEquals(FraudSignal.of(RiskMagnitude.HIGH, RiskImpactScale.SIGNIFICANT,"TransactionVelocityRule","Transaction too much quick"),result);
    }
}
