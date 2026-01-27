package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatusPolicy;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class OpenFraudIncidentInteractor implements com.github.sentinel.pay.application.services.OpenFraudIncidentUseCase {
   private final FraudIncidentStatusPolicy policy;
   private final FraudIncidentRepository fraudIncidentRepository;

   public FraudIncident openIncident(FraudDecision fraudDecision, Transaction tx){

          FraudIncidentStatus status =  policy.statusFromDecision(fraudDecision.getFraudDecisionType());
          var incident = FraudIncident.create(
                  tx.getClientAccountId(),
                  tx.getFraudIncidentId(),
                  fraudDecision.getTransactionId(),
                  fraudDecision.getFraudDecisionId(),
                  fraudDecision.getRiskPoint(),
                  fraudDecision.getAccountId(),
                  status,
                  Instant.now()
          );

            return fraudIncidentRepository
                    .save(incident);

   }
}
