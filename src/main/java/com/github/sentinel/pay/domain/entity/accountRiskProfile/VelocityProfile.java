package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class   VelocityProfile{
        private UUID id;
       private  BigDecimal avgTxPerHour;
       private  BigDecimal avgTxPerDay;
        private int currentHourCount;
         private        int peakTxPerHour;
        private long samples;
       private  Instant currentHourBucket;
       private  Instant lastUpdated;


    public static VelocityProfile initial() {
        return new VelocityProfile(
                UUID.randomUUID(),
        
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                0
                ,0,
                0,
                null,
                null
        );
    }

    public void observe(Instant txTime) {

        if(currentHourBucket ==null){
                currentHourBucket=txTime;
        }
        
            Instant hourBucket = txTime.truncatedTo(ChronoUnit.HOURS);


              
             int hourCount = currentHourBucket.equals(hourBucket)
                    ? currentHourCount + 1
                    : 1;

            int newPeak = Math.max(peakTxPerHour, hourCount);

            BigDecimal newAvgHour = avgTxPerHour
                    .multiply(BigDecimal.valueOf(this.getSamples()))
                    .add(BigDecimal.ONE)
                    .divide(BigDecimal.valueOf(this.getSamples() + 1), RoundingMode.HALF_UP);
 
             BigDecimal newAvgTxPerDay = avgTxPerDay
                     .multiply(BigDecimal.valueOf(this.getSamples()))
                    .add(BigDecimal.ONE)
                    .divide(BigDecimal.valueOf(this.getSamples() + 1), RoundingMode.HALF_UP);
                    
                    
                 this.avgTxPerHour=   newAvgHour;
                 this.avgTxPerDay =  newAvgTxPerDay;
                 this.currentHourCount=   hourCount;
                 this.peakTxPerHour=   newPeak;
                 this.samples=    this.getSamples() + 1;
                 this.currentHourBucket=  txTime;
                 this.lastUpdated=  hourBucket;

        
        }

    public boolean isBursting() {
    return peakTxPerHour > avgTxPerHour.multiply(BigDecimal.valueOf(3)).intValue();
    }
}