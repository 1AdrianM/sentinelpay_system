package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.usecases.CreateAdminUserUseCase;
import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateAdminUserInteractor implements CreateAdminUserUseCase {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CreateAdminUserInteractor.class);
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    public CreateAdminUserInteractor(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void execute() {
      logger.atInfo().log("Checking for existing system admin user"); 
        if (!userRepository.existsByRole(UserRole.SYSTEM_ADMIN)) {
          logger.atInfo().log("No system admin user found. Creating default admin user.");
        var hashedPassword= encoder.encode("sentinel_admin123@");
        logger.atInfo().log("Hashed default admin password");
      User user =  User.builder()
                .id(User.generateUserId())
              .name("Administrator")
                .clientAccountId(ClientAccountId.generateRiskProfileId())
                .email("admin@sentinel.com")
                .role(UserRole.SYSTEM_ADMIN)
                .enabled(true)
                .passwordHash(hashedPassword)
                .build();
      logger.atInfo().log("Saving default admin user to repository");
      userRepository.save(user);
    }
    }
}
