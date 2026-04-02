package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.user.MinimalUserInfoDto;

public interface GetUserInfoForSideBarUseCase {
    MinimalUserInfoDto execute();
}
