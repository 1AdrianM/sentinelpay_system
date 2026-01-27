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
public class AuthServiceInteractor implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserPrincipal authenticate(String email, String password) {
      User user=  userRepository.findByEmail(email);
      if(!passwordEncoder.matches(password, user.getPasswordHash())){
          throw new UnauthorizedException("password doesnt match");
      };

      return new UserPrincipal(user.getId().id(),user.getClientAccountId(),user.getEmail(),user.getRole());
    }

    @Override
    public UserPrincipal register(UserDto dto) {
        if (dto==null){
            throw new RuntimeException("User Dto found to be null");
        }
     var hashedPassword= passwordEncoder.encode(dto.getPassword());
        User user = new User(User.generateUserId(), ClientAccountId.generateRiskProfileId(),dto.getName(),dto.getEmail(),hashedPassword, UserRole.READ_ONLY,true);
      User savedUser= userRepository.save(user);
      return new UserPrincipal(savedUser.getId().id(),user.getClientAccountId(),savedUser.getEmail(),savedUser.getRole());
    }
}
