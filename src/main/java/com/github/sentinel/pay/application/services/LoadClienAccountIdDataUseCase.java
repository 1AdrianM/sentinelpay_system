package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.user.SettingInfoDto;
import com.github.sentinel.pay.application.dto.user.UserDto;

public interface LoadClienAccountIdDataUseCase {
    SettingInfoDto execute();
}
