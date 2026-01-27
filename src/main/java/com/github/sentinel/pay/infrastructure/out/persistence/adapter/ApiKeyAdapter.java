package com.github.sentinel.pay.infrastructure.out.persistence.adapter;

import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKey;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.ApiKeyRepository;
import com.github.sentinel.pay.domain.utils.ApiKeySecurity;
import com.github.sentinel.pay.infrastructure.out.persistence.EntityModels.ApiKeyEntity;
import com.github.sentinel.pay.infrastructure.out.persistence.base.BasePersistenceAdapter;
import com.github.sentinel.pay.infrastructure.out.persistence.jpa.ApiKeyEntityRepository;
import com.github.sentinel.pay.infrastructure.out.persistence.mapper.ApiKeyMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ApiKeyAdapter extends BasePersistenceAdapter<ApiKey, ApiKeyEntity, UUID> implements ApiKeyRepository {
  private final  ApiKeyEntityRepository entityRepository;
  private final ApiKeyMapper apiKeyMapper;
    public ApiKeyAdapter(ApiKeyMapper entityMapper, ApiKeyEntityRepository entityRepository, ApiKeyMapper apiKeyMapper) {
        super(entityMapper, entityRepository);
        this.entityRepository=entityRepository;
        this.apiKeyMapper = apiKeyMapper;
    }

    @Override
    public Optional<ApiKey> findValid(String rawKey) {
       var hashedKey = ApiKeySecurity.hash(rawKey);
       var entity= entityRepository.findByHashedKey(hashedKey);
       var domainEntity = apiKeyMapper.EntityModelToDomainEntity(entity);
     return Optional.of(domainEntity);

    }

    @Override
    public List<ApiKey> findAllByClientAccountId(ClientAccountId clientAccountId) {
    return this.entityRepository
             .findAllByClientAccountId(clientAccountId.id())
             .orElseThrow(()->new RuntimeException("not able find api key list"))
             .stream()
             .map(apiKeyMapper::EntityModelToDomainEntity)
             .collect(Collectors.toList());
    }
}
