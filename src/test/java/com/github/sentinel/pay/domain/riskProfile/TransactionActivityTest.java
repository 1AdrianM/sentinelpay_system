package com.github.sentinel.pay.domain.riskProfile;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.deprecated.TransactionActivity;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

public class TransactionActivityTest {
@Test
    void ShouldReturnHighRiskFromTooMuchTransactionsActivityTest(){
  Instant now = Instant.now();
  // int lastTransaction = now.get(ChronoField.HOUR_OF_DAY);
    Deque<Instant> lastHourTxs = new ArrayDeque<>();
    lastHourTxs.add(now.plusSeconds(30));
    lastHourTxs.add(now.plusSeconds(10));
    lastHourTxs.add(now.plusSeconds(5));
    lastHourTxs.add(now.plusSeconds(2));
    lastHourTxs.add(now.plusSeconds(1));

    TransactionActivity transactionActivity = new TransactionActivity(0,lastHourTxs);
 var result=  transactionActivity.transactionVelocityRisk(now);
    Assertions.assertEquals(RiskContribution.HIGH, result);

}
 @Test
    void ShouldReturnHighRiskWhenTransactionPassTheNumberOfFiveUnderTheMinute(){
     Instant now = Instant.now();
    // int lastTransaction = now.get(ChronoField.HOUR_OF_DAY);
     Deque<Instant> lastHourTxs = new ArrayDeque<>();
     lastHourTxs.add(now.plusSeconds(1));
     lastHourTxs.add(now.plusSeconds( 5));
     lastHourTxs.add(now.plusSeconds(5));
     lastHourTxs.add(now.plusSeconds(5));
     lastHourTxs.add(now.plusSeconds(5));
     TransactionActivity transactionActivity = new TransactionActivity(0,lastHourTxs);
     var result=  transactionActivity.exceedsTransactionPerFiveMinute(now);
     Assertions.assertEquals(RiskContribution.HIGH, result);

 }

}
