package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.user.UserDto;

public interface UpdateUserUseCase {
    void execute(UserDto user);
}
