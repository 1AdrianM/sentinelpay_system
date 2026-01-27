package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudDecisionEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface FraudDecisionEntityRepository extends EntityRepository<FraudDecisionEntity, UUID> {
     FraudDecisionEntity findByTransactionId(UUID transactionId);

     FraudDecisionEntity findByAccountId(UUID accountId);
     @Query("""
             SELECT d FROM FraudDecisionEntity d
             WHERE d.clientAccountId =:clientAccountId
             ORDER BY d.issuedAt DESC
             """)
     Page<FraudDecisionEntity> findDecisions(@Param("clientAccountId") UUID clientAccountId, Pageable pageable);

}
