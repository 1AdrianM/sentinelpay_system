package com.github.sentinel.pay.infrastructure.out.persistence.mapper;

public interface EntityMapper<D,E> {

  E  domainEntityToEntityModel(D domainEntity);
   D EntityModelToDomainEntity(E entityModel);
}
