package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.dto.user.UserDto;

import java.util.List;

public interface ListApiKeyByUserIdUseCase {
    List<ApiKeyDto> execute(UserDto userDto);
}
