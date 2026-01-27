package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.incidents.IncidentDetails;
import com.github.sentinel.pay.application.dto.riskProfile.AccountSnapShot;
import com.github.sentinel.pay.application.dto.transaction.TransactionDetails;
import com.github.sentinel.pay.application.services.GetIncidentDetailsByIncidentIdUseCase;
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
   private final FraudIncidentRepository fraudIncidentRepository;
   private final TransactionRepository transactionRepository;
   private final RiskProfileRepository riskProfileRepository;
    @Override
    public IncidentDetails execute(FraudIncidentId incidentId) {
      FraudIncident incident= fraudIncidentRepository.findByFraudIncidentId(incidentId);
    Transaction tx=  transactionRepository.findByIncidentId(incidentId);
    AccountRiskProfile riskProfile= riskProfileRepository.findOrCreateByAccountId(incident.getAccountId());

    return IncidentDetails.builder()
            .incidentId(incident.getIncidentId().id())
            .status(incident.getStatus().name())
            .riskScore(incident.getRiskScore().value())
            //TODO Need to refine this
            .transactionDetails(new TransactionDetails(
                    tx.getMoney().amount().intValue()
                    ,tx.getMoney().currency().name(),
                    tx.getTransactionType().name(),
                    tx.getLocation().toString(),
                    tx.getTimestamp()))
            .accountSnapShot(new AccountSnapShot(
                    riskProfile.getRiskLevel().name()
                    ,riskProfile.getMonetaryProfile().mean().intValue()
                    ,riskProfile.getLocationProfile().mostFrequentLocation().toString()
                    ,riskProfile.getIncidents().length()))
            .build();
    }
}
