package com.github.sentinel.pay.infrastructure.out.persistence.mapper.adapters;

import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKey;
import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKeyId;
import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKeyStatus;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.ApiKeyEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.ApiKeyMapper;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyMapperImpl implements ApiKeyMapper {
    @Override
    public ApiKeyEntity domainEntityToEntityModel(ApiKey domainEntity) {
        return ApiKeyEntity.builder()
                .id(domainEntity.getId().id())
                .name(domainEntity.getName())
                .clientAccountId(domainEntity.getClientAccountId().id())
                .status(domainEntity.getStatus().name())
                .hashedKey(domainEntity.getHashedKey())
                .createdAt(domainEntity.getCreatedAt())
                .lastUsedAt(domainEntity.getLastUsedAt())
                .build();
    }

    @Override
    public ApiKey EntityModelToDomainEntity(ApiKeyEntity entityModel) {
        return ApiKey.builder()
                .id(new ApiKeyId(entityModel.getId()))
                .name(entityModel.getName())
                .status(ApiKeyStatus.valueOf(entityModel.getStatus()))
                .hashedKey(entityModel.getHashedKey())
                .clientAccountId(new ClientAccountId(entityModel.getId()))
                .lastUsedAt(entityModel.getLastUsedAt())
                .createdAt(entityModel.getCreatedAt())
                .build();
    }
}
