package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.services.DeleteApiKeyUseCase;
import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKey;
import com.github.sentinel.pay.domain.repository.ApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class DeleteApiKeyInteractor implements DeleteApiKeyUseCase {
private  final ApiKeyRepository apiKeyRepository;

    @Override
    public void execute(UUID keyId) {
        if(!apiKeyRepository.existsById(keyId)){
            throw new RuntimeException("ApiKey with Such keyId does not exist");
        }
        apiKeyRepository.deleteById(keyId);
        // Verificar que la key pertenece al usuario
     }
}
