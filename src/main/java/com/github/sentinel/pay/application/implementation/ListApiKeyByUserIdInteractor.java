package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.services.ListApiKeyByUserIdUseCase;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.ApiKeyRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListApiKeyByUserIdInteractor implements ListApiKeyByUserIdUseCase {
    @Override
    public List<ApiKeyDto> execute(UserDto userDto) {
   return List.of();

    }
}
