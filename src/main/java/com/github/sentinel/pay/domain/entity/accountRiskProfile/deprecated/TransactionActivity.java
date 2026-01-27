package com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated;

import com.github.sentinel.pay.domain.entity.risk.RiskContribution;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Deque;

public record TransactionActivity(
        int lastHourTransactionAt,
        Deque<Instant> mostRecentTransactionsTimeStamps
) {

    private static final int CRITICAL_INTERVAL_MS = 2000; // 2 segundos
    private static final int SUSPICIOUS_INTERVAL_MS = 5000; // 5 segundos
    private static final int WARNING_INTERVAL_MS = 30000;   // 30 segundos
    private static final int MAX_TRANSACTIONS_PER_MINUTE = 5;

     public  RiskContribution transactionVelocityRisk( Instant currentTimeOfTransaction){
        var diff=   Duration.between(currentTimeOfTransaction, mostRecentTransactionsTimeStamps.getLast()).toMillis();
           if (diff < SUSPICIOUS_INTERVAL_MS) {
               return RiskContribution.HIGH;
           } else if (diff < WARNING_INTERVAL_MS) {
               return RiskContribution.MEDIUM;
           } else {
               return RiskContribution.NONE;
           }
       }
          public RiskContribution exceedsTransactionPerFiveMinute(Instant ahora) {

               Instant unMinutoAtras = ahora.minus(1, ChronoUnit.MINUTES);
               var count =this.mostRecentTransactionsTimeStamps.stream().filter(tx -> tx.isAfter(unMinutoAtras) && tx.isBefore(ahora.plusSeconds(1))).count();
              System.out.println("Count number: " + count);
              if (count > 5){
               return RiskContribution.HIGH;
           }
       return RiskContribution.NONE;
       }
}


