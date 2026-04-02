package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;
import java.util.UUID;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MonetaryProfile{
    private      UUID id;
     private   BigDecimal mean;
     private   BigDecimal m2;
    private    BigDecimal maxAmountObserved;
     private   BigDecimal minAmountObserved;
    private    long samples;
    private     Instant lastUpdated;
   private static final MathContext MC = new MathContext(10);


    public static MonetaryProfile initial(BigDecimal firstAmount) {
        return new MonetaryProfile(
                 UUID.randomUUID(),
                
                firstAmount,
                BigDecimal.ZERO,
                firstAmount,
                firstAmount,
                1,
                Instant.now()
        );
    }
    public void observe(BigDecimal amount){
            long n = this.getSamples() + 1;

            BigDecimal delta = amount.subtract(mean);
            BigDecimal newMean = mean.add(delta.divide(BigDecimal.valueOf(n), MC));
            BigDecimal delta2 = amount.subtract(newMean);
            BigDecimal newM2 = m2.add(delta.multiply(delta2));
 
            this.mean=newMean;
             this.m2= newM2;
             this.maxAmountObserved=  maxAmountObserved.max(amount);
             this.minAmountObserved= minAmountObserved.min(amount);
            this.samples= n;
            this.lastUpdated=Instant.now();
            

    }
    public BigDecimal variance() {
        if (samples < 2) return BigDecimal.ZERO;
        return m2.divide(BigDecimal.valueOf(samples - 1), MC);
    }
    // Simple BigDecimal sqrt
    private static BigDecimal sqrt(BigDecimal value, MathContext mc) {
        return new BigDecimal(Math.sqrt(value.doubleValue()), mc);
    }
    public BigDecimal standardDeviation() {
        return sqrt(variance(), MC);
    }
    public boolean isAnomalous(BigDecimal txAmount) {
        return txAmount.subtract(this.getMean())
                .abs()
                .compareTo(this.standardDeviation().multiply(BigDecimal.valueOf(3))) > 0;
    }
}