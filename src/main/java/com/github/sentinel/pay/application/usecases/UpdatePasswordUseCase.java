package com.github.sentinel.pay.application.usecases;

public interface UpdatePasswordUseCase  {
   void execute(String currentPassword, String newPassword);
}
