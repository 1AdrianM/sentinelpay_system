package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.dashboard.SystemStatusDto;

public interface GetSystemStatusUseCase {
    SystemStatusDto execute();
}
