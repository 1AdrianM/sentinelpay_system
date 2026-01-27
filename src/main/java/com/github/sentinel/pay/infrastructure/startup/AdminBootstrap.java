package com.github.sentinel.pay.infrastructure.startup;

import com.github.sentinel.pay.application.services.CreateAdminUserUseCase;
import com.github.sentinel.pay.domain.entity.auth.user.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class AdminBootstrap implements ApplicationRunner {

    private final CreateAdminUserUseCase createAdminUser;

    public AdminBootstrap(CreateAdminUserUseCase createAdminUser) {
        this.createAdminUser = createAdminUser;
    }
    @Override
    public void run(ApplicationArguments args) {
        createAdminUser.execute();
    }
}

