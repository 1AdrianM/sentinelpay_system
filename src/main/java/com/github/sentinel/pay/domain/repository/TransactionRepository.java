package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

import java.time.Instant;
import java.util.List;

public interface TransactionRepository {

    Transaction findByAccountId(AccountId accountId);
    List<Transaction> findConfirmedFraudulentTransactions();

    Transaction save(Transaction tx);

    Transaction findByIncidentId(FraudIncidentId incidentId);

    int findTransactionCountThisDay(Instant now, ClientAccountId clientAccountId);
}