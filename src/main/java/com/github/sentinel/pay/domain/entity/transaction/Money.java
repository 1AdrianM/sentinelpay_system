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

    public static Money of(String amount, String currency) {
      if(currency.isBlank() ||currency.isEmpty()) {
          throw new RuntimeException("currency is empty or blank");
      }
      if(amount.isEmpty()|| amount.isBlank()) {
      throw new RuntimeException("amount is empty or blank");
      }

return new Money(new BigDecimal(amount),Currency.valueOf(currency));
    }

    public boolean isGreaterThan(BigDecimal averageAmount){
      return this.amount.compareTo(averageAmount)>0;
   }
}

