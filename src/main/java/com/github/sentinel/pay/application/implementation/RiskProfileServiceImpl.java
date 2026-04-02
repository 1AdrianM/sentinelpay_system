package com.github.sentinel.pay.application.implementation;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.services.RiskProfileService;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskLevelPolicy;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiskProfileServiceImpl implements RiskProfileService{
    
    private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RiskProfileService.class);
    private final RiskProfileRepository riskProfileRepository;

    @Override
    public AccountRiskProfile findOrCreate(AccountId accountId) {
        logger.atInfo().log("Attempting to find or create risk profile for account ID: {}", accountId.id());
    return riskProfileRepository.findByAccountId(accountId).orElseGet(
        () -> createNewProfile(accountId)
    );
   
 }
private AccountRiskProfile createNewProfile(AccountId accountId){
     ClientAccountId clientAccountId= ClientAccountId.of(TenantContextHolder.get().getClientAccountId());
    if (clientAccountId ==null){
     throw new RuntimeException("clientAccountId of tenant missing in the context");
   }

   AccountRiskProfile newProfile= AccountRiskProfile.initial(
     clientAccountId,
    AccountRiskProfile.generateRiskProfileId(),
    accountId
);
 
  return riskProfileRepository.save(newProfile); 

}
    @Override
    public AccountRiskProfile update(AccountRiskProfile riskProfile) {

       logger.atInfo().log("Starting account risk profile update process for account ID: {}", riskProfile.getAccountId().id());    
       //Evaluando el riskLevel basado en el historial y score       
        logger.atInfo().log("Evaluated risk level: {} for account ID: {}", riskProfile.getRiskLevel().name(), riskProfile.getAccountId().id());
        //actualizando risk level       
        //profile persistiendo actualizaciones
        return riskProfileRepository.update(riskProfile);
    }
    
}
