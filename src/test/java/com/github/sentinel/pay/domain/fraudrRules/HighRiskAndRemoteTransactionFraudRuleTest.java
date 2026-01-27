package com.github.sentinel.pay.domain.fraudrRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.AverageCurrencyTransaction;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.TransactionActivity;
import com.github.sentinel.pay.domain.entity.fraudRules.HighRiskAndRemoteTransactionFraudRule;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class HighRiskAndRemoteTransactionFraudRuleTest {

    @Test
      void ShouldReturnHighRiskContributionWhenTransactionTypeIsHighRiskAndRemote(){
        Transaction tx = new Transaction(
                1L,
                200L,
                TransactionType.CRYPTO_TRANSFER,
                new Money(new BigDecimal("6000"), Currency.USD),
                Instant.now(),
                new Location("RD","SD"),
                null
        );
        AccountRiskProfile riskProfile = new AccountRiskProfile(
                200L,
                new BigDecimal("2000"),
                1,
                new TransactionActivity(2,null),
                new Location("SD","DO") ,
                new AverageCurrencyTransaction(Currency.USD,5L),
                RiskLevel.LOW,
                Instant.now()
        );
        HighRiskAndRemoteTransactionFraudRule rule = new HighRiskAndRemoteTransactionFraudRule();
       var result= rule.evaluateTransaction(tx, riskProfile);
        Assertions.assertEquals(RiskContribution.MEDIUM, result);
    }
}
