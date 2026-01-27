package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public record   VelocityProfile(
         BigDecimal avgTxPerHour,
         BigDecimal avgTxPerDay,
         int currentHourCount,
                 int peakTxPerHour,
         long samples,
         Instant currentHourBucket,
         Instant lastUpdated
){

    public static VelocityProfile initial() {
        return new VelocityProfile(
                null,null,0,0,0,null,null
        );
    }

    public VelocityProfile update(Instant txTime) {

            Instant hourBucket = txTime.truncatedTo(ChronoUnit.HOURS);

             int hourCount = currentHourBucket.equals(hourBucket)
                    ? currentHourCount + 1
                    : 1;

            int newPeak = Math.max(peakTxPerHour, hourCount);

            BigDecimal newAvgHour = avgTxPerHour
                    .multiply(BigDecimal.valueOf(samples))
                    .add(BigDecimal.ONE)
                    .divide(BigDecimal.valueOf(samples + 1), RoundingMode.HALF_UP);

            return new VelocityProfile(
                    newAvgHour,
                    avgTxPerDay, //TODO se hace igual pero por día
                    hourCount,
                    newPeak,
                    samples + 1,
                    txTime,
                    hourBucket
            );
        }

    public boolean isBursting() {
    return peakTxPerHour > avgTxPerHour.multiply(BigDecimal.valueOf(3)).intValue();
    }
}