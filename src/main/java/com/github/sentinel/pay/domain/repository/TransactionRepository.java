package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    Transaction findByAccountId(AccountId accountId);
    List<Transaction> findConfirmedFraudulentTransactions();

    Transaction save(Transaction tx);

    int findTransactionCountThisDay(Instant now, ClientAccountId clientAccountId);

   Optional<Transaction> findById(UUID transactionId);

   Transaction findByIncidentId(FraudIncidentId id);
}