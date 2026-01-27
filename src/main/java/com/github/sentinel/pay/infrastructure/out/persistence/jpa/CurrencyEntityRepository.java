package com.github.sentinel.pay.infrastructure.out.persistence.jpa;

import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.CurrencyProfileEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;

import java.util.UUID;

public interface CurrencyEntityRepository extends EntityRepository<CurrencyProfileEntity, UUID> {}
