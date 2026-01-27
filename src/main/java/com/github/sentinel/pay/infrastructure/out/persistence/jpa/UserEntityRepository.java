package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.UserEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends EntityRepository<UserEntity, UUID> {

    @Query("""
            SELECT u from UserEntity u
            WHERE u.email =:email
            """)
    Optional<UserEntity> findByEmail(String email);

    boolean existsByRole(String role);

    Optional<UserEntity> findByClientAccountId(UUID id);
}
