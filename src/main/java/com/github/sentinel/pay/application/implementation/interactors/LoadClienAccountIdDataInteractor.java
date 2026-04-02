package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.dto.user.SettingInfoDto;
import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.usecases.LoadClienAccountIdDataUseCase;
import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.ApiKeyRepository;
import com.github.sentinel.pay.domain.repository.UserRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class LoadClienAccountIdDataInteractor implements LoadClienAccountIdDataUseCase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LoadClienAccountIdDataInteractor.class);
    private final ApiKeyRepository apiKeyRepository;
    private  final UserRepository   userRepository;

    @Override
    public SettingInfoDto execute() {
logger.atInfo().log("Starting client account data loading process");
        TenantContext tenantContext= TenantContextHolder.get();
        ClientAccountId clientAccountId= new ClientAccountId(tenantContext.getClientAccountId());
        logger.atInfo().log("Retrieved tenant context with client account ID: {}", clientAccountId);
        logger.atInfo().log("clientID DATA: {}", clientAccountId.id());
           User user=        userRepository.findByClientAccountId(clientAccountId);
        logger.atInfo().log("User Name for settings: {}", user.getName());
        var apiKeyList  =  apiKeyRepository.findAllByClientAccountId(clientAccountId);
        logger.atInfo().log("Retrieved {} API keys for client account ID: {}", apiKeyList.size(), clientAccountId);
        var apiKeyDtos = apiKeyList.stream().map(a-> ApiKeyDto.builder()
                        .id(a.getId().id())
                        .nameKey(a.getName())
                        .maskedValue(a.getHashedKey())
                        .createdAt(a.getCreatedAt())
                        .build())
                        .collect(Collectors.toList());
                        logger.atInfo().log("Mapped API keys to DTOs for client account ID: {}", clientAccountId);
     //   System.out.println("last key name: "+apiKeyList.get(apiKeyList.size()-1).nameKey);
     logger.atInfo().log("Assembling setting information DTO for client account ID: {}", clientAccountId);
        return SettingInfoDto.builder()
                .userDto(UserDto.builder()
                        .email(user.getEmail())
                        .password(user.getPasswordHash())
                        .name(user.getName())
                        .build())
                .apiKeyDtoList(apiKeyDtos)
                .build();
    }
}
