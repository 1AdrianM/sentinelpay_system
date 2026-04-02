package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.incidents.IncidentDetails;
import com.github.sentinel.pay.application.dto.riskProfile.AccountSnapShot;
import com.github.sentinel.pay.application.dto.transaction.TransactionDetails;
import com.github.sentinel.pay.application.services.RiskProfileService;
import com.github.sentinel.pay.application.usecases.GetIncidentDetailsByIncidentIdUseCase;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import com.github.sentinel.pay.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetIncidentDetailsByIncidentIdInteractor implements GetIncidentDetailsByIncidentIdUseCase {
        private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GetIncidentDetailsByIncidentIdInteractor.class);
   private final FraudIncidentRepository fraudIncidentRepository;
   private final TransactionRepository transactionRepository;
   private final RiskProfileRepository riskProfileRepository;
  
  
   @Override
  public IncidentDetails execute(FraudIncidentId incidentId) {
        logger.atInfo().log("Starting incident details retrieval process for incident ID: {}", incidentId);
      FraudIncident incident= fraudIncidentRepository.findByFraudIncidentId(incidentId);
      if (incident == null){
        throw new RuntimeException("incident not found");
      }
      logger.atInfo().log("Retrieved fraud incident with ID: {}", incidentId);
    Transaction tx=  transactionRepository.findByIncidentId(incidentId);
    if (tx==null){
        throw new RuntimeException("transaction not found");
    }
      logger.atInfo().log("Retrieved transaction with ID: {}", incidentId);
      AccountRiskProfile riskProfile= riskProfileRepository.findByAccountId(incident.getAccountId()).orElseThrow(() -> new RuntimeException("Account risk profile not found"));
    
      logger.atInfo().log("Retrieved account risk profile for account ID: {}", incident.getAccountId().id());
        
       return buildIncidentDetailsResponse(incident, tx, riskProfile);
    }


  private IncidentDetails buildIncidentDetailsResponse(FraudIncident incident, Transaction tx, AccountRiskProfile riskProfile){      
    return IncidentDetails.builder()
            .incidentId(incident.getIncidentId().id())
            .status(incident.getStatus().name())
            .riskScore(incident.getRiskScore().value())
            //TODO Need to refine this
            .transactionDetails(TransactionDetails.builder()
            .Currency(tx.getMoney().currency().name())
            .Amount( tx.getMoney().amount().intValue())
            .Type(  tx.getTransactionType().name())
            .TimeStamp(  tx.getTimestamp())
            .Location(tx.getLocation().toString())
            .build())
            .accountSnapShot(new AccountSnapShot(
                    riskProfile.getRiskLevel().name()
                    ,riskProfile.getMonetaryProfile().getMean().intValue()
                    ,riskProfile.getLocationProfile().mostFrequentLocation().toString()
                    ,riskProfile.getIncidents().length()))
            .build();
    }
}
