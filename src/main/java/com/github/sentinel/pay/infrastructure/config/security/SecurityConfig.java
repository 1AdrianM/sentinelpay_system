package com.github.sentinel.pay.infrastructure.config.security;

import com.github.sentinel.pay.domain.entity.auth.user.UserRole;
import com.github.sentinel.pay.infrastructure.config.security.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;

        // Cadena para el demo web (con sesión)
        @Bean
        @Order(1)
        public SecurityFilterChain webSecurity(HttpSecurity http) throws Exception {

            SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
           //store session
            http.securityContext((context) -> context.securityContextRepository(securityContextRepository))

                    .securityMatcher("/**")
                    .csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults())
                    .sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    )
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/signin", "/signup", "/do-signin", "/do-signup").permitAll()
                            .requestMatchers("/css/**", "/js/**", "/webjars/**", "/.well-known/**").permitAll()
                            .requestMatchers( "/admin/**").hasRole(UserRole.TENANT_ADMIN.name())


                            .anyRequest().authenticated()
                    )
                    .formLogin(form -> form
                            .loginPage("/signin")
                            .loginProcessingUrl("/do-signin")
                            .defaultSuccessUrl("/dashboard")
                            .failureUrl("/signin?error=true")
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/signin?logout=true")
                    );
            return http.build();
        }

        // Cadena para la API (stateless con API keys/JWT)
        @Bean
        @Order(2)
        public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
            http
                    .securityMatcher("/api/**") // rutas API
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session ->
                            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().authenticated()
                    )
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }
    }
