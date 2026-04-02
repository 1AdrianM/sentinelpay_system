package com.github.sentinel.pay.infrastructure.in.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.github.sentinel.pay.application.usecases.GetUserInfoForSideBarUseCase;
import com.github.sentinel.pay.infrastructure.config.security.SecurityUserDetails;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
   private final GetUserInfoForSideBarUseCase getUserInfoForSideBarUseCase;
    @ModelAttribute("isReadOnly")
    public boolean isReadOnly() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_READ_ONLY"));
        }
        return false;
    }
@ModelAttribute("currentUser")
    public UserDetails getCurrentUser(@AuthenticationPrincipal SecurityUserDetails currentUser) {
        // Esto inyecta el usuario logueado automáticamente en el modelo de Thymeleaf
    
        return currentUser; 
    } 

}
