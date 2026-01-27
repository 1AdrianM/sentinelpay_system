package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FraudIncidentRepository {


    FraudIncident findOpenByTransactionId(TransactionId transactionId);

    List<FraudIncident> findOpenByAccountId(AccountId accountId);

    FraudIncident save(FraudIncident incident);

    FraudIncident resolve(FraudIncident incident);

    List<FraudIncident> findAllByClientAccountId(ClientAccountId clientAccountId);

    FraudIncident findByFraudIncidentId(FraudIncidentId incidentId);

    int findAllConfirmedFraudIncidentsCount(ClientAccountId clientAccountId);

    int findOpenIncidentCount(ClientAccountId clientAccountId);

    List<FraudIncident> findAllConfirmedFraudIncidents(ClientAccountId clientAccountId);

    List<FraudIncident> findAllByAccountId(AccountId accountID, Pageable pageable);
}