package com.github.sentinel.pay.domain.entity.transactionAggregate;

import java.math.BigDecimal;

public record Money(
        BigDecimal amount,
         Currency currency

) {
}
