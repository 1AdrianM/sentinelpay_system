package com.github.sentinel.pay.domain.entity.transaction;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.shared.Location;
 
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

  @Getter
  @AllArgsConstructor
  @Builder(access = AccessLevel.PUBLIC)
public class Transaction {
    private TransactionId transactionId;
      private ClientAccountId clientAccountId;
      private AccountId accountId;
    private TransactionType transactionType;
    private Location location;
    private Money money;//record
     private Instant timestamp;
     private Channel   channel;



      public static TransactionId generateTransactionId(){
        return new TransactionId(UUID.randomUUID());
    }

      public static Transaction create(TransactionId transactionId,ClientAccountId clientAccountId,  AccountId accountId,Location location, Money money, TransactionType transactionType, Channel channel, Instant now) {
      return new Transaction(transactionId,clientAccountId,accountId,transactionType,location,money,now, channel);
      }

   
  }
