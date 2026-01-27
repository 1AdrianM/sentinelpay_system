package com.github.sentinel.pay.infrastructure.out.persistence.jpa;


import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.AccountRiskProfileEntity;
 import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org. springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRiskProfileEntityRepository extends EntityRepository<AccountRiskProfileEntity, UUID>{

    Optional<AccountRiskProfileEntity> findByAccountId(UUID accountId);

    List<AccountRiskProfileEntity> findAllByOrderByAverageRiskScoreDesc();

     @Query("""
             SELECT COUNT(r)
             FROM AccountRiskProfileEntity r
             WHERE r.riskLevel='RESTRICTED'
             OR r.riskLevel='HIGH'
             AND r.clientAccountId=:clientAccountId
             """)
     int findByHighAndRestrictedAccountCount(UUID clientAccountId);

    @Query("""
    SELECT r
    FROM AccountRiskProfileEntity r
    WHERE r.clientAccountId = :clientAccountId
    ORDER BY r.lastUpdated DESC
""")
    List<AccountRiskProfileEntity> findLastRiskProfiles(
            @Param("clientAccountId") UUID clientAccountId,
          Pageable pageable);



}
