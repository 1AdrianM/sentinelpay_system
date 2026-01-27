package com.github.sentinel.pay.infrastructure.out.persistence.adapter;

import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.UserRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.UserEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.base.BasePersistenceAdapter;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.UserEntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.generic.EntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.EntityMapper;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.UserMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UserAdapter extends BasePersistenceAdapter<User, UserEntity, UUID> implements UserRepository {
   private final UserEntityRepository entityRepository;
    private final UserMapper entityMapper;
    public UserAdapter(UserMapper entityMapper, UserEntityRepository entityRepository, UserEntityRepository entityRepository1, UserMapper entityMapper1) {
        super(entityMapper, entityRepository);
        this.entityRepository = entityRepository1;
        this.entityMapper = entityMapper;
    }

    @Override
    public User findByEmail(String email) {
        var user = entityRepository.findByEmail(email)
                .map(entityMapper::EntityModelToDomainEntity)
                .orElseThrow();
        System.out.println("Usuario encontrado por email: "+user);
        return user;
    }

    @Override
    public boolean existsByRole(UserRole userRole) {
        return this.entityRepository.existsByRole(userRole.name());
    }

    @Override
    public User findByClientAccountId(ClientAccountId clientAccountId) {
     UserEntity user= this.entityRepository.findByClientAccountId(clientAccountId.id()).orElseThrow(()-> new RuntimeException(""));
    return entityMapper.EntityModelToDomainEntity(user);
    }
}
