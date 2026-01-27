package com.github.sentinel.pay.infrastructure.config.security.strategy;

import com.github.sentinel.pay.application.exceptions.UnauthorizedException;
import com.github.sentinel.pay.domain.entity.auth.user.UserPrincipal;
import com.github.sentinel.pay.infrastructure.config.security.SecurityUserDetails;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class SessionAuthenticator implements AuthenticationStrategy {

    @Override
    public void authenticate(HttpServletRequest request) {

          /*  Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                throw new UnauthorizedException("No authenticated user");
            }

            SecurityUserDetails principal =
                    (SecurityUserDetails) authentication.getPrincipal();

            TenantContext ctx = new TenantContext();
            //check
            //TODO
            ctx.put( principal.getPrincipal().userId());
            TenantContextHolder.set(ctx);
        }*/
    }

}
