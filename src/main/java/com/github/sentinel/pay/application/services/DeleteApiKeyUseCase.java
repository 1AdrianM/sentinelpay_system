package com.github.sentinel.pay.application.services;

import java.util.UUID;

public interface DeleteApiKeyUseCase {
   void execute(UUID keyId);
}
