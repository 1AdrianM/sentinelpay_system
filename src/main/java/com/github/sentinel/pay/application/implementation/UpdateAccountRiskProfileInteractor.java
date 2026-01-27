package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.services.UpdateAccountRiskProfileUseCase;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskLevelPolicy;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateAccountRiskProfileInteractor implements UpdateAccountRiskProfileUseCase {
   private final RiskProfileRepository accountRiskProfileRepository;
    private final RiskLevelPolicy riskLevelPolicy;

    @Override
    public AccountRiskProfile updateRiskProfile(AccountRiskProfile riskProfile) {

       //Evaluando el riskLevel basado en el historial y score
        RiskLevel riskLevel = riskLevelPolicy.evaluate(riskProfile);
        //actualizando risk level
        riskProfile.updateRiskLevel(riskLevel);
        //profile persistiendo actualizaciones
        return accountRiskProfileRepository.update(riskProfile);
    }
}
