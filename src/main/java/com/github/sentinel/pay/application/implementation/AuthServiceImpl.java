package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.exceptions.UnauthorizedException;
import com.github.sentinel.pay.application.services.AuthService;
import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.entity.auth.user.UserPrincipal;
import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserPrincipal authenticate(String email, String password) {
     logger.atInfo().log("Attempting to authenticate user with email: {}", email);

      User user=  userRepository.findByEmail(email);
        if (user==null){
            logger.atError().log("Authentication failed: No user found with email: {}", email);
            throw new UnauthorizedException("User not found");
        }

      if(!passwordEncoder.matches(password, user.getPasswordHash())){
          logger.atError().log("Authentication failed for user with email: {}", email);
          throw new UnauthorizedException("password doesnt match");
      };

      logger.atInfo().log("Authentication successful for user with email: {}", email);

      return new UserPrincipal(user.getId().id(),user.getClientAccountId(),user.getEmail(),user.getRole());
    }

    @Override
    public UserPrincipal register(UserDto dto) {
        if (dto==null){
            logger.atError().log("Registration failed: UserDto is null");
            throw new RuntimeException("User Dto found to be null");
        }
     
        logger.atInfo().log("Executing password hashing for user registration");
     var hashedPassword= passwordEncoder.encode(dto.getPassword());
     
     logger.atInfo().log("Creating new user");
     User user = new User(User.generateUserId(), ClientAccountId.generateRiskProfileId(),dto.getName(),dto.getEmail(),hashedPassword, UserRole.READ_ONLY,true);
     
     logger.atInfo().log("Saving new user to repository");
     User savedUser= userRepository.save(user);

      return new UserPrincipal(savedUser.getId().id(),savedUser.getClientAccountId(),savedUser.getEmail(),savedUser.getRole());
    }
}
