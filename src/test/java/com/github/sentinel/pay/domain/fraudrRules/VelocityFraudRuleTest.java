package com.github.sentinel.pay.domain.fraudrRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.AverageCurrencyTransaction;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.TransactionActivity;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
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


        AccountRiskProfile riskProfile = new AccountRiskProfile(
                200L,
                new BigDecimal("2000"),
                3,
                new TransactionActivity(2, lastHourTx),
                new Location("SD","DO") ,
                new AverageCurrencyTransaction(Currency.USD,5L),
                RiskLevel.LOW,

                Instant.now()
        );
        Transaction tx = new Transaction(
                1L,
                200L,
                TransactionType.CRYPTO_TRANSFER,
                new Money(new BigDecimal("6000"), Currency.USD),
                lastHourTx.getLast().plus(Duration.ofMinutes(2)),
                new Location("RD","SD"),
                null
        );

        TransactionVelocityFraudRule rule = new TransactionVelocityFraudRule();
        var result =rule.evaluateTransaction(tx,riskProfile);
        Assertions.assertEquals(RiskContribution.HIGH,result);
    }
}
