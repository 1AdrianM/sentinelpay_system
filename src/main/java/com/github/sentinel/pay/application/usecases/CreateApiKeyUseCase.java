package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;

public interface CreateApiKeyUseCase {
    ApiKeyDto execute(String keyName);
}
