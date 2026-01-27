package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.MonetaryProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import java.util.UUID;

public interface MonetaryEntityRepository extends EntityRepository<MonetaryProfileEntity, UUID> {}
