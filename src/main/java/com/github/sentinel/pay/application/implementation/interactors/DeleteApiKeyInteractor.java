package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.usecases.DeleteApiKeyUseCase;
import com.github.sentinel.pay.domain.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DeleteApiKeyInteractor implements DeleteApiKeyUseCase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(DeleteApiKeyInteractor.class);
private  final ApiKeyRepository apiKeyRepository;

    @Override
    public void execute(UUID keyId) {
        logger.atInfo().log("Starting API key deletion process for key ID: {}", keyId);
        if(!apiKeyRepository.existsById(keyId)){
            throw new RuntimeException("ApiKey with Such keyId does not exist");
        }
        apiKeyRepository.deleteById(keyId);
        logger.atInfo().log("API key deleted successfully");
        // Verificar que la key pertenece al usuario
     }
}
