package com.github.sentinel.pay.infrastructure.in.web.controller;

import com.github.sentinel.pay.application.services.GetRiskProfileByAccountIdUseCase;
import com.github.sentinel.pay.application.services.UpdateAccountRiskProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class AccountController {
   private final GetRiskProfileByAccountIdUseCase getRiskProfileByAccountIdUseCase;
    private final UpdateAccountRiskProfileUseCase updateAccountRiskProfileUseCase;
    //needs an account ID, we need to see the template to adapt the dto
    @GetMapping("/accounts/{accountId}")
    public String getAccountProfile(@PathVariable String accountId, Model model) {
        // Mock data will be added here later
       var dto =getRiskProfileByAccountIdUseCase.execute(accountId);
        model.addAttribute("riskProfile", dto);
        return "account-profile";
    }
    @GetMapping("/accounts/")
    public String getAccounts(Model model){

        return "accounts";
    }
    @PatchMapping("/accounts/{accountId}")
    public String updateRiskProfile(@PathVariable String accountId, Model model){
        return "";
    }
}
