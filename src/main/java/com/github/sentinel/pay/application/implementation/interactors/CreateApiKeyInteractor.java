package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.usecases.CreateApiKeyUseCase;
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
   private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CreateApiKeyInteractor.class);
    private final ApiKeyRepository apiKeyRepository;
    @Override
    public ApiKeyDto execute(String keyName) {
      logger.atInfo().log("Starting API key creation process for key name: {}", keyName);
       
      TenantContext tenantContext= TenantContextHolder.get();
       
       logger.atInfo().log("Retrieved tenant context with client account ID: {}", tenantContext.getClientAccountId());
       
       ClientAccountId clientAccountId= new ClientAccountId(tenantContext.getClientAccountId());
       
       logger.atInfo().log("Generating raw API key");
       
       String raw = ApiKeySecurity.generate();
       
       if (raw == null || raw.isEmpty()) {
           logger.atError().log("Failed to generate a valid API key");
           throw new RuntimeException("Failed to generate API key");
       }
       ApiKey apiKey= ApiKey.generate(clientAccountId,keyName,raw);

       var savedKey = apiKeyRepository.save(apiKey);
       logger.atInfo().log("API key created and saved successfully");
         return ApiKeyDto
                 .builder()
                 .value(savedKey.getRawKey())
                 .maskedValue(savedKey.getHashedKey())
                 .nameKey(savedKey.getName())
                 .build();
        // Crear nueva API Key
     }
}
