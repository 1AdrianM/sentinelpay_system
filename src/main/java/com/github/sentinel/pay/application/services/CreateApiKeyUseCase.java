package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;

public interface CreateApiKeyUseCase {
    ApiKeyDto execute(String keyName);
}
