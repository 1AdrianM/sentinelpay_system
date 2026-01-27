package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKey;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApiKeyRepository {
    Optional<ApiKey> findValid(String rawKey);
    ApiKey save(ApiKey apiKey);
   void deleteById(UUID id);
boolean existsById(UUID id);
   List<ApiKey> findAllByClientAccountId(ClientAccountId clientAccountId);
}
