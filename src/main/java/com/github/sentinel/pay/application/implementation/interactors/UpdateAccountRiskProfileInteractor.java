package com.github.sentinel.pay.application.implementation.interactors;

 
import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.services.RiskProfileService;
import com.github.sentinel.pay.application.usecases.UpdateAccountRiskProfileUseCase;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UpdateAccountRiskProfileInteractor implements UpdateAccountRiskProfileUseCase{
    private final RiskProfileService riskProfileService;

    @Override
    public void execute(AccountRiskProfile accountRiskProfile) {
        if (accountRiskProfile==null){
            throw new EntityNotFoundException("risk profile null");
        }    
        
        riskProfileService.update(accountRiskProfile);
    }

    
}
