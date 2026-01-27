package com.github.sentinel.pay.application.dto.user;

import com.github.sentinel.pay.application.dto.apiKey.ApiKeyDto;
import lombok.Builder;

import java.util.List;
@Builder
public class SettingInfoDto {
    public UserDto userDto;
    public List<ApiKeyDto> apiKeyDtoList;
}
