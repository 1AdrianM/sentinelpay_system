package com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated;

import com.github.sentinel.pay.domain.entity.shared.Currency;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record AverageCurrencyTransaction(
        Currency averageCurrency,
        List<Currency> currencyList

) {
   public Currency getAverageCurrency(){
       return this.currencyList.stream()
               .collect(Collectors.groupingBy(p-> p, Collectors.counting()))
               .entrySet()
               .stream()
               .max(Map.Entry.comparingByKey())
               .map(Map.Entry::getKey)
               .orElse(null);


   }
}
