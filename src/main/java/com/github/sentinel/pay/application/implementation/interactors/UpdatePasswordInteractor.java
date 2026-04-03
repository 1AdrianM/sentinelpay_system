package com.github.sentinel.pay.application.implementation.interactors;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.usecases.UpdatePasswordUseCase;

@Service
public class UpdatePasswordInteractor implements UpdatePasswordUseCase  {

    @Override
    public void execute(String currentPassword, String newPassword) {
    }
    
}
