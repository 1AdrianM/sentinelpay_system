package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.FraudIncidentEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FraudIncidentEntityRepository extends EntityRepository<FraudIncidentEntity, UUID> {

     FraudIncidentEntity findByTransactionIdAndStatus(UUID transactionId,
                                                      FraudIncidentStatus status);

    List<FraudIncidentEntity> findByAccountIdAndStatus(
            UUID accountId,
            FraudIncidentStatus status
    );
    List<FraudIncidentEntity> findAllByClientAccountId(UUID clientAccountId);

    @Query("""
              SELECT COUNT(i)
              FROM FraudIncidentEntity i
              WHERE i.status='CONFIRMED_FRAUD'
              AND i.clientAccountId =:clientAccountId
            """)
     int findAllConfirmedFraudIncidentsCount(UUID clientAccountId);

@Query("""
        SELECT COUNT(i)
        FROM FraudIncidentEntity i
        WHERE i.status ='OPEN'
        AND i.clientAccountId=:clientAccountId
        """)
         int findOpenIncidentCount (UUID clientAccountId);

         @Query("""
                  SELECT i
                 FROM FraudIncidentEntity i
                 WHERE i.status='CONFIRMED_FRAUD'
                 AND   i.clientAccountId=:clientAccountId
                 """
         )
         List<FraudIncidentEntity> findAllConfirmedFraudIncidents (UUID clientAccountId);
    @Query("""
                  SELECT i
                 FROM FraudIncidentEntity i
                 WHERE i.accountId=:accountId
                 ORDER BY i.openedAt DESC
                 """
    )
    Page<FraudIncidentEntity> findAllByAccountId(AccountId accountID, Pageable pageable);
}