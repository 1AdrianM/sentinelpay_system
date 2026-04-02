package com.github.sentinel.pay.application.usecases;

import java.util.UUID;

public interface DeleteApiKeyUseCase {
   void execute(UUID keyId);
}
