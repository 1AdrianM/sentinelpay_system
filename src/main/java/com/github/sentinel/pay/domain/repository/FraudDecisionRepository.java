package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FraudDecisionRepository {

    FraudDecision findByTransactionId(TransactionId transactionId);

    FraudDecision findByAccountId(AccountId accountId);

    List<FraudDecision> findLastTwoDecision(ClientAccountId clientAccountId);

    Optional<FraudDecision> findById(UUID id);

    FraudDecision update(UUID id, FraudDecision fraudDecision);

     List<FraudDecision> findAllByClientAccountId(ClientAccountId clientAccountId, Pageable pageable);
    FraudDecision save(FraudDecision fraudDecision);
}