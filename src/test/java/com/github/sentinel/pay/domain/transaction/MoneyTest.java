package com.github.sentinel.pay.domain.transaction;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.shared.Currency;
import com.github.sentinel.pay.domain.entity.transaction.Money;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void shouldNotAllowNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Money(new BigDecimal("-10"), Currency.USD));
    }
    @Test
    void shouldReturnTrueWhenTransactionAmountIsBiggerRiskProfileAverageTransaction(){
        Transaction tx = new Transaction(new Money(new BigDecimal("10"),Currency.USD));
        AccountRiskProfile riskProfile = new AccountRiskProfile(new BigDecimal("40"));
       var result= tx.getMoney().isGreaterThan(riskProfile.getAverageAmount());
        assertEquals(false, result);
    }
}
