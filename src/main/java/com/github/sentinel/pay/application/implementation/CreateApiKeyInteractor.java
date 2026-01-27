package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.services.CreateApiKeyUseCase;
import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKey;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.ApiKeyRepository;
import com.github.sentinel.pay.domain.utils.ApiKeySecurity;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateApiKeyInteractor implements CreateApiKeyUseCase {
    private final ApiKeyRepository apiKeyRepository;
    @Override
    public ApiKeyDto execute(String keyName) {
       TenantContext tenantContext= TenantContextHolder.get();
       ClientAccountId clientAccountId= new ClientAccountId(tenantContext.getClientAccountId());
       String raw = ApiKeySecurity.generate();
       ApiKey apiKey= ApiKey.generate(clientAccountId,keyName,raw);
       var savedKey = apiKeyRepository.save(apiKey);
         return ApiKeyDto
                 .builder()
                 .value(raw)
                 .maskedValue(savedKey.getHashedKey())
                 .nameKey(savedKey.getName())
                 .build();
        // Crear nueva API Key
     }
}
