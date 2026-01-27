package com.github.sentinel.pay.domain.repository;

import com.github.sentinel.pay.domain.entity.auth.user.User;
import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;

public interface UserRepository {
    User findByEmail(String email);

    User save(User user);

    boolean existsByRole(UserRole userRole);

    User findByClientAccountId(ClientAccountId clientAccountId);
}
