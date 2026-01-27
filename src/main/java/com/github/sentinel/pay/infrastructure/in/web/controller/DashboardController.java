package com.github.sentinel.pay.infrastructure.in.web.controller;

import com.github.sentinel.pay.application.services.GetDashBoardDataUseCase;
import com.github.sentinel.pay.domain.entity.auth.user.UserPrincipal;
import com.github.sentinel.pay.infrastructure.config.security.SecurityUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {
private final GetDashBoardDataUseCase getDashBoardDataUseCase;

@GetMapping("/dashboard")
    public String dashboard(    @AuthenticationPrincipal SecurityUserDetails userDetails
,Model model) {
        // Mock data will be added here later
        System.out.println("🔥 ENTRÓ /dashboard");
        UserPrincipal user = userDetails.getPrincipal();
        System.out.println(user.email());
        var dto=   getDashBoardDataUseCase.execute();
        model.addAttribute("dashboard",dto);
        return "dashboard";
    }
}

//<div class="alert alert-info">
//    🔍 Modo solo lectura — acciones deshabilitadas
//</div>