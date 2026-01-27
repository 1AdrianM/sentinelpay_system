package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.entity.auth.user.UserId;
import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.UserEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.UserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity domainEntityToEntityModel(User domainEntity) {
        return UserEntity
                .builder()
                .id(domainEntity.getId().id())
                .clientAccountId(domainEntity.getClientAccountId().id())
                .name(domainEntity.getName())
                .email(domainEntity.getEmail())
                .role(domainEntity.getRole().name())
                .passwordHash(domainEntity.getPasswordHash())
                .enabled(domainEntity.isEnabled())
                .build();
    }

    @Override
    public User EntityModelToDomainEntity(UserEntity entityModel) {
        return User.builder()
                .id(new UserId(entityModel.getId()))
                .clientAccountId(new ClientAccountId(entityModel.getClientAccountId()))
                .name(entityModel.getName())
                .email(entityModel.getEmail())
                .enabled(entityModel.isEnabled())
                .passwordHash(entityModel.getPasswordHash())
                .role(UserRole.valueOf(entityModel.getRole()))
                .build();
    }
}
