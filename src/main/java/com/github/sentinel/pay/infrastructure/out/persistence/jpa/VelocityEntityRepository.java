package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.VelocityProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import java.util.UUID;

public interface VelocityEntityRepository extends EntityRepository<VelocityProfileEntity, UUID> {
}

