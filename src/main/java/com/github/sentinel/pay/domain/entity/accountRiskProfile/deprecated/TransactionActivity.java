package com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated;


 
public record TransactionActivity(
     //   int lastHourTransactionAt,
      //  Deque<Instant> mostRecentTransactionsTimeStamps
) {
/*
    private static final int CRITICAL_INTERVAL_MS = 2000; // 2 segundos
    private static final int SUSPICIOUS_INTERVAL_MS = 5000; // 5 segundos
    private static final int WARNING_INTERVAL_MS = 30000;   // 30 segundos
    private static final int MAX_TRANSACTIONS_PER_MINUTE = 5;

     public FraudSignal transactionVelocityRisk(Instant currentTimeOfTransaction){
        var diff=   Duration.between(currentTimeOfTransaction, mostRecentTransactionsTimeStamps.getLast()).toMillis();
           if (diff < SUSPICIOUS_INTERVAL_MS) {
               return FraudSignal.HIGH();
           } else if (diff < WARNING_INTERVAL_MS) {
               return FraudSignal.MEDIUM;
           } else {
               return FraudSignal.NONE;
           }
       }
          public FraudSignal exceedsTransactionPerFiveMinute(Instant ahora) {

               Instant unMinutoAtras = ahora.minus(1, ChronoUnit.MINUTES);
               var count =this.mostRecentTransactionsTimeStamps.stream().filter(tx -> tx.isAfter(unMinutoAtras) && tx.isBefore(ahora.plusSeconds(1))).count();
              System.out.println("Count number: " + count);
              if (count > 5){
               return FraudSignal.HIGH;
           }
       return FraudSignal.NONE;
       }

 */
}


