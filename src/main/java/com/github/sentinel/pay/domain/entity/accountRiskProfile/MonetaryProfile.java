package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.util.Collections.max;

public record MonetaryProfile(
        BigDecimal mean,
        BigDecimal m2,
        BigDecimal maxAmountObserved,
        BigDecimal minAmountObserved,
        long samples

) {
    private static final MathContext MC = new MathContext(10);

    public static MonetaryProfile initial(BigDecimal firstAmount) {
        return new MonetaryProfile(
                firstAmount,
                BigDecimal.ZERO,
                firstAmount,
                firstAmount,
                1
        );
    }
    public MonetaryProfile update(BigDecimal amount){
            long n = this.samples() + 1;

            BigDecimal delta = amount.subtract(mean);
            BigDecimal newMean = mean.add(delta.divide(BigDecimal.valueOf(n), MC));

            BigDecimal delta2 = amount.subtract(newMean);
            BigDecimal newM2 = m2.add(delta.multiply(delta2));

            return new MonetaryProfile(
                    newMean,
                    newM2,
                    maxAmountObserved.max(amount),
                    minAmountObserved.min(amount),
                    n
            );

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
        return txAmount.subtract(this.mean())
                .abs()
                .compareTo(this.standardDeviation().multiply(BigDecimal.valueOf(3))) > 0;
    }
}