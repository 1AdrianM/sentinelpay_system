package com.github.sentinel.pay.infrastructure.config.ratelimiting;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.filter.OncePerRequestFilter;

import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RateLimitingFilter extends OncePerRequestFilter {
     private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();
   
     @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                String clientAccountId= TenantContextHolder.get().getClientAccountId().toString();
        Bucket bucket = buckets.computeIfAbsent(clientAccountId, k-> createBucketForClientAccountId(clientAccountId));
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.getWriter().write("Too many requests - rate limit exceeded");    
    }
    
    }

     private Bucket createBucketForClientAccountId(String clientAccountId) {
            

            return Bucket.builder()
            .addLimit( Bandwidth.builder()
            .capacity(5) // max tokens
            .refillGreedy(5, Duration.ofSeconds(10)) // refill all every minute
            .build())

            .addLimit(Bandwidth.builder()
            .capacity(15)
            .refillGreedy(15, Duration.ofMinutes(1))
            .build())
            
            .build();
    }
}
