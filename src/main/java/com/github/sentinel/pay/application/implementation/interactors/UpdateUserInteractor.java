package com.github.sentinel.pay.application.implementation.interactors;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.services.UserService;
import com.github.sentinel.pay.application.usecases.UpdateUserUseCase;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UpdateUserInteractor implements UpdateUserUseCase{
 private final UserService userService;
    @Override
    public void execute(UserDto user) {
        userService.update(user);
    }
    
}
