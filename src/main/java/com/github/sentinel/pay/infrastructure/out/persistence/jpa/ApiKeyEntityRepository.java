package com.github.sentinel.pay.infrastructure.out.persistence.jpa;


import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.ApiKeyEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiKeyEntityRepository extends EntityRepository<ApiKeyEntity, UUID> {


    ApiKeyEntity findByHashedKey(String hashedKey);

    Optional<List<ApiKeyEntity>> findAllByClientAccountId(UUID id);
}
