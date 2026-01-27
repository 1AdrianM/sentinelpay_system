package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.services.CreateAdminUserUseCase;
import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateAdminUserInteractor implements CreateAdminUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    public CreateAdminUserInteractor(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void execute() {
        if (!userRepository.existsByRole(UserRole.SYSTEM_ADMIN)) {
        var hashedPassword= encoder.encode("sentinel_admin123@");
      User user =  User.builder()
                .id(User.generateUserId())
              .name("Administrator")
                .clientAccountId(ClientAccountId.generateRiskProfileId())
                .email("admin@sentinel.com")
                .role(UserRole.SYSTEM_ADMIN)
                .enabled(true)
                .passwordHash(hashedPassword)
                .build();
      userRepository.save(user);
    }
    }
}
