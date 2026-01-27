package com.github.sentinel.pay.domain.entity.transaction;

import com.github.sentinel.pay.domain.entity.shared.Currency;

import java.math.BigDecimal;

public record Money(
        BigDecimal amount,
         Currency currency

) {
  public Money(BigDecimal amount, Currency currency){
     if (amount.intValue() < 0){
        throw new IllegalArgumentException("Not Allowed to receive negative amount");
     }
     this.amount = amount;
     this.currency = currency;

  }
   public boolean isGreaterThan(BigDecimal averageAmount){
      return this.amount.compareTo(averageAmount)>0;
   }
}

