package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.user.UserDto;
import com.github.sentinel.pay.domain.entity.auth.user.UserPrincipal;
import org.springframework.stereotype.Service;

public interface AuthService {
   UserPrincipal authenticate(String email, String password);

   UserPrincipal register(UserDto dto);
}
