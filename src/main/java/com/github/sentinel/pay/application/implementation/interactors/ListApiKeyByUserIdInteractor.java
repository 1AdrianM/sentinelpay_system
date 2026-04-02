package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.usecases.ListApiKeyByUserIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
 
@Service
@RequiredArgsConstructor
public class ListApiKeyByUserIdInteractor implements ListApiKeyByUserIdUseCase {
    @Override
    public List<ApiKeyDto> execute(UserDto userDto) {
   return List.of();

    }
}
