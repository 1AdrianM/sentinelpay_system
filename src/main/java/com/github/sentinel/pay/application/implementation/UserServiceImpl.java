package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.services.UserService;
import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void update(UserDto user) {
     logger.atInfo().log("Starting user update process for user with email: {}", user.getEmail());
       String hashedPassword = "";
       User foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser == null) {
            logger.atError().log("User with email: {} not found for update", user.getEmail());
            throw new RuntimeException("User not found");
        }
       if(!user.getPassword().isEmpty() || !user.getPassword().isBlank()) {
        logger.atInfo().log("Password provided for update, executing password hashing for user with email: {}", user.getEmail());
         hashedPassword=  passwordEncoder.encode(user.getPassword());
       }
       logger.atInfo().log("Updating user details for user with email: {}", user.getEmail());
        foundUser.update(user.getName(),hashedPassword);
        // Actualizar datos del usuario
        logger.atInfo().log("Saving updated user details for user with email: {}", user.getEmail());
        userRepository.save(foundUser);
    }
}
