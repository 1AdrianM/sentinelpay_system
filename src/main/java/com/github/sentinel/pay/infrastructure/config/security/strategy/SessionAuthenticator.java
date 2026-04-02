package com.github.sentinel.pay.infrastructure.config.security.strategy;


import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;



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
