package com.github.sentinel.pay.infrastructure.config.security;

import com.github.sentinel.pay.domain.entity.auth.user.UserPrincipal;
import com.github.sentinel.pay.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        System.out.println("🔥🔥🔥 loadUserByUsername CALLED: " + email);
        var user = userRepository.findByEmail(email);
         if(user==null){
             throw new RuntimeException("user found to be null");

         }
         UserPrincipal principal = new UserPrincipal(
                user.getId().id(),
                user.getClientAccountId(),
                user.getEmail(),
                user.getRole()
        );

        return new SecurityUserDetails(
                principal,
                user.getPasswordHash() // encrypted
        );
}
}
