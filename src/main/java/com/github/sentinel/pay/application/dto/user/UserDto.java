package com.github.sentinel.pay.application.dto.user;

import com.github.sentinel.pay.domain.entity.auth.user.UserId;
import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    @Email
     private String email;
    @Size(min = 6)
    private String password;
 }
