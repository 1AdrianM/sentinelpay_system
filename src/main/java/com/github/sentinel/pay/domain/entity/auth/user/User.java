package com.github.sentinel.pay.domain.entity.auth.user;

import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@Getter
@Builder
@AllArgsConstructor
public class User {

    private UserId id;
    private ClientAccountId clientAccountId;
    private String name;
     private String email;
     private String passwordHash;
    private UserRole role;
    private boolean enabled;


    public static UserId generateUserId(){
        return new UserId(UUID.randomUUID());
    };

    public void update(String name, @Size(min = 6) String password) {
    if(!name.isEmpty()) this.name=name;
    if(!password.isEmpty()) this.passwordHash =password;

    }

}
