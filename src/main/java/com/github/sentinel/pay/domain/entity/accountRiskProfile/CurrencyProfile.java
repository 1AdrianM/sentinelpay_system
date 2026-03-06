package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.shared.Currency;

import java.util.HashMap;
import java.util.Map;

public record CurrencyProfile(
           Map<Currency,Integer> currencyCount,
           long sample
) {
    public static CurrencyProfile empty() {
        Map<Currency,Integer> newMap  = new HashMap<>();
        return new CurrencyProfile(newMap,0);
    }

    public CurrencyProfile observe(Currency currency){
        var newMap  = new HashMap<>(currencyCount);
        newMap.merge(currency,1,Integer::sum);
       return new CurrencyProfile(newMap, sample + 1);
    }

    public double confidence(Currency currency) {
        if (sample == 0) return 0.0;
        return currencyCount.getOrDefault(currency, 0) / (double) sample;
    }

    public double maxConfidence() {
        if (sample == 0) return 0.0;
        return currencyCount.values()
                .stream()
                .mapToDouble(c -> c / (double) sample)
                .max()
                .orElse(0.0);
    }

    public double diversity() {
        return 1.0 - maxConfidence();
    }
    public Currency mostRepeatedCurrency(){
       return currencyCount
                       .entrySet()
                       .stream()
                       .max(Map.Entry.comparingByKey()).map(Map.Entry::getKey)
                       .orElse(null);
    }
    public FraudSignal riskFromDiversityScore(){
    if (diversity() > 0.60) return FraudSignal.of(RiskMagnitude.HIGH, RiskImpactScale.SIGNIFICANT,"UnusualCurrency","High Diversity was Found in Currency data");
    if (diversity() > 0.35) return FraudSignal.of(RiskMagnitude.MEDIUM,RiskImpactScale.MODERATE,"UnusualCurrency","Medium Diversity was found in currency data");
    return FraudSignal.of(RiskMagnitude.NEGLIGIBLE,RiskImpactScale.MINIMAL,"Monetary","Low Diversity found in currency data");
    }

    public boolean isUnusualCurrency(Currency txCurrency){
    return !mostRepeatedCurrency().equals(txCurrency) && diversity() > 0.6;
    }
}
