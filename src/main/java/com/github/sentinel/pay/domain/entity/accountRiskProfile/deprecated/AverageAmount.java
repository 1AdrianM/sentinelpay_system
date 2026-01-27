package com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record AverageAmount(
        BigDecimal averageAmount,
        List<BigDecimal> transactionAmountList

) {
    public BigDecimal getAverageAmountFromList(){
                return  transactionAmountList.stream()
                   .collect(Collectors.groupingBy(p-> p, Collectors.counting()))
                   .entrySet()
                   .stream()
                   .max(Map.Entry.comparingByKey())
                   .map(Map.Entry::getKey)
                   .orElse(null);

    }
}
