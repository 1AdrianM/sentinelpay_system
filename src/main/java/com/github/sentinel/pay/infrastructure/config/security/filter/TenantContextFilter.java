package com.github.sentinel.pay.infrastructure.config.security.filter;

import com.github.sentinel.pay.infrastructure.config.security.SecurityUserDetails;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(SecurityProperties.DEFAULT_FILTER_ORDER + 1)
public class TenantContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        try {
            Authentication auth =
                    SecurityContextHolder.getContext().getAuthentication();

            if (auth != null
                    && auth.isAuthenticated()
                    && auth.getPrincipal() instanceof SecurityUserDetails sud) {

                TenantContext ctx = new TenantContext();
                ctx.put(sud.getPrincipal().clientAccountId().id());
                TenantContextHolder.set(ctx);
            }

            filterChain.doFilter(request, response);

        } finally {
            TenantContextHolder.clear();
        }
    }
}
