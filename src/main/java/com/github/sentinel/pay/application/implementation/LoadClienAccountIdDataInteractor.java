package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.dto.user.SettingInfoDto;
import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.services.LoadClienAccountIdDataUseCase;
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
    private final ApiKeyRepository apiKeyRepository;
    private  final UserRepository   userRepository;

    @Override
    public SettingInfoDto execute() {

        TenantContext tenantContext= TenantContextHolder.get();
        ClientAccountId clientAccountId= new ClientAccountId(tenantContext.getClientAccountId());
        System.out.println("clientID DATA: "+clientAccountId.id());
           User user=        userRepository.findByClientAccountId(clientAccountId);
        System.out.println("User Name for settings: "+user.getName());
        var apiKeyList  =  apiKeyRepository
                          .findAllByClientAccountId(clientAccountId)
                         .stream().map(a-> ApiKeyDto.builder()
                        .id(a.getId().id())
                        .nameKey(a.getName())
                        .maskedValue(a.getHashedKey())
                        .createdAt(a.getCreatedAt())
                        .build())
                        .collect(Collectors.toList());
     //   System.out.println("last key name: "+apiKeyList.get(apiKeyList.size()-1).nameKey);
        return SettingInfoDto.builder()
                .userDto(UserDto.builder()
                        .email(user.getEmail())
                        .password(user.getPasswordHash())
                        .name(user.getName())
                        .build())
                .apiKeyDtoList(apiKeyList)
                .build();
    }
}
