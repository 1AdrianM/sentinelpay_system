package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.LocationProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import java.util.UUID;

public interface LocationEntityRepository extends EntityRepository<LocationProfileEntity, UUID> {}
