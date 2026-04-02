package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskImpactScale;
import com.github.sentinel.pay.domain.entity.risk.RiskMagnitude;
import com.github.sentinel.pay.domain.entity.shared.Currency;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CurrencyProfile{
        private UUID id;
        private  Map<Currency,Integer> currencyCount;
        private   long sample;
        private  Instant lastUpdated;

    public static CurrencyProfile initial() {
         
        return new CurrencyProfile(UUID.randomUUID(),
         new HashMap<Currency,Integer>(),
        0, null);
    }

    public void observe(Currency currency){
        currencyCount.merge(currency,1,Integer::sum);
       this.sample++;
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
                       .orElseThrow(() -> new IllegalStateException("No currency observed yet"));
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
