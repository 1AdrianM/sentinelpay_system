package com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<E,I> extends JpaRepository<E,I> {
}
