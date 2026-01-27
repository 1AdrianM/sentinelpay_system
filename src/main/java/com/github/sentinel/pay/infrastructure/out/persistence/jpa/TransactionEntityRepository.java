package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.TransactionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionEntityRepository extends EntityRepository<TransactionEntity, UUID> {

    TransactionEntity findByAccountId(UUID accountId);

    @Query(
            """
                    select t from TransactionEntity t
                    where t.confirmedFraud = true
                    """
    )
    List<TransactionEntity> findAllFraudulentTransactions();

    TransactionEntity findByFraudIncidentId(UUID fraudIncidentId);

    @Query("""
                SELECT COUNT(t)
                FROM TransactionEntity t
                WHERE t.timestamp BETWEEN :startOfDay AND :endOfDay
                  AND t.clientAccountId = :clientAccountId
            """)
    int findTransactionCountThisDay(@Param("startOfDay") Instant startOfDay,
                                    @Param("endOfDay") Instant endOfDay,
                                    @Param("clientAccountId") UUID clientAccountId);

}