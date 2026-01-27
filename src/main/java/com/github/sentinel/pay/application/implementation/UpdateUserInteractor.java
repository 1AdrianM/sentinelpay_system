package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.application.services.UpdateUserUseCase;
import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserInteractor implements UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(UserDto user) {
       String hashedPassword = "";
       User foundUser = userRepository.findByEmail(user.getEmail());
       if(!user.getPassword().isEmpty() || !user.getPassword().isBlank()) {
         hashedPassword=  passwordEncoder.encode(user.getPassword());
       }
        foundUser.update(user.getName(),hashedPassword);
        // Actualizar datos del usuario
        userRepository.save(foundUser);
    }
}
