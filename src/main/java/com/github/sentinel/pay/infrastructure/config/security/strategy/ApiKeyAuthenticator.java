package com.github.sentinel.pay.infrastructure.config.security.strategy;

import com.github.sentinel.pay.application.exceptions.UnauthorizedException;
import com.github.sentinel.pay.domain.entity.auth.apiKey.ApiKey;
import com.github.sentinel.pay.domain.repository.ApiKeyRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiKeyAuthenticator implements AuthenticationStrategy {
          private final ApiKeyRepository apiKeyRepository;

    @Override
    public void authenticate(HttpServletRequest request) {


        String rawKey = request.getHeader("X-API-KEY");
        if(rawKey == null){
         throw new UnauthorizedException("Raw key found to be null");
        }

        ApiKey apiKey = apiKeyRepository.findValid(rawKey)
                .orElseThrow(UnauthorizedException::new);
        apiKey.markUsed();
        TenantContext ctx= new TenantContext();
        ctx.put(apiKey.getClientAccountId().id());
        TenantContextHolder.set(ctx);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        apiKey.getClientAccountId(),     // TU objeto
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_API"))
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
