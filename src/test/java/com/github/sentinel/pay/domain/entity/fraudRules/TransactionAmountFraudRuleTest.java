package com.github.sentinel.pay.domain.entity.fraudRules;

import java.math.BigDecimal;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.MonetaryProfile;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.transaction.Money;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionAmountFraudRuleTest {

    private TransactionAmountFraudRule rule;

    @Mock
    private Transaction mockTransaction;
    @Mock
    private AccountRiskProfile mockAccountRiskProfile;
    @Mock
    private MonetaryProfile mockMonetaryProfile;
    @Mock
    private Money mockMoney;

    @BeforeEach
    void setUp() {
        rule = new TransactionAmountFraudRule();
        when(mockTransaction.getMoney()).thenReturn(mockMoney);
        when(mockAccountRiskProfile.getMonetaryProfile()).thenReturn(mockMonetaryProfile);
    }

    @Test
    void evaluateTransaction_returnsCriticalSignal_whenAmountIsAnomalous() {
        // Arrange
        BigDecimal anomalousAmount = new BigDecimal("1000.0");
        when(mockMoney.amount()).thenReturn(anomalousAmount);
        when(mockMonetaryProfile.isAnomalous(anomalousAmount)).thenReturn(true);

        // Act
        FraudSignal result = rule.evaluateTransaction(mockTransaction, mockAccountRiskProfile);

        // Assert
        assertEquals(RiskMagnitude.CRITICAL.getScore(), result.score());
        assertEquals(RiskImpactScale.SIGNIFICANT.getWeight(), result.weight());
        assertEquals("TransactionAmountRule", result.ruleTriggered());
        assertEquals("Transaction Amount is thrice the usual amount ", result.description());
    }

    @Test
    void evaluateTransaction_returnsNegligibleSignal_whenAmountIsNotAnomalous() {
        // Arrange
        BigDecimal usualAmount = new BigDecimal("100.0");
        when(mockMoney.amount()).thenReturn(usualAmount);
        when(mockMonetaryProfile.isAnomalous(usualAmount)).thenReturn(false);

        // Act
        FraudSignal result = rule.evaluateTransaction(mockTransaction, mockAccountRiskProfile);

        // Assert
        assertEquals(RiskMagnitude.NEGLIGIBLE.getScore(), result.score());
        assertEquals(RiskImpactScale.REDUCED.getWeight(), result.weight());
        assertEquals("TransactionAmountRule", result.ruleTriggered());
        assertEquals("Transaction amount is usual amount", result.description());
    }
}
