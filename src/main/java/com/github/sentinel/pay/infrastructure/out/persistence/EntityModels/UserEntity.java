package com.github.sentinel.pay.infrastructure.out.persistence.EntityModels;

import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private UUID id;
    private UUID clientAccountId;
    @Column(nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
     private String role;
    private boolean enabled;
}
