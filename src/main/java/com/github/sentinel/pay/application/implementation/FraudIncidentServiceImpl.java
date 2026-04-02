package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.services.FraudIncidentService;
import com.github.sentinel.pay.application.services.RiskProfileService;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.audit.ActorType;
import com.github.sentinel.pay.domain.entity.audit.AuditEntry;
import com.github.sentinel.pay.domain.entity.audit.AuditReason;
import com.github.sentinel.pay.domain.entity.audit.AuditSnapshot;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotKind;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatusPolicy;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.domain.policies.AuditReasonPolicy;
import com.github.sentinel.pay.domain.repository.AuditAppenderRepository;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
 
@RequiredArgsConstructor
@Service
public class FraudIncidentServiceImpl implements FraudIncidentService {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FraudIncidentServiceImpl.class);
   private final FraudIncidentRepository fraudIncidentRepository;
   private final RiskProfileService riskProfileService;
   private final RiskProfileRepository riskProfileRepository;
   private final AuditAppenderRepository auditAppender;
    private final FraudIncidentStatusPolicy   statusPolicy;
   private final AuditReasonPolicy auditReasonPolicy;

    @Override
    public FraudIncident open(FraudDecision fraudDecision, Transaction tx) {
  
          logger.atInfo().log("Starting fraud incident opening process for transaction ID: {}", tx.getTransactionId());
          FraudIncidentStatus status =  statusPolicy.statusFromDecision(fraudDecision.getFraudDecisionType());
          logger.atInfo().log("Determined fraud incident status: {} for transaction ID: {}", status, tx.getTransactionId());
        
          var incident = FraudIncident.create(
                  tx.getClientAccountId(),
                  FraudIncident.generateIncidentId(),
                  tx.getTransactionId(),
                  fraudDecision.getFraudDecisionId(),
                  fraudDecision.getRiskPoint(),
                  fraudDecision.getAccountId(),
                  status,
                  Instant.now()
          );
        logger.atInfo().log("Created fraud incident for transaction ID: {} with status: {}", tx.getTransactionId(), status);
        FraudIncident savedIncident = fraudIncidentRepository.save(incident);
        logger.atInfo().log("Saved fraud incident with ID: {} for transaction ID: {}", savedIncident.getIncidentId(), tx.getTransactionId());
        return savedIncident;
    }


    @Override
    public IncidentResponseDto resolve(TransactionId transactionId, String status) {
    logger.atInfo().log("Starting fraud incident resolution process for transaction ID: {}", transactionId);
       FraudIncident incident=  fraudIncidentRepository.findIncidentOpenByTransactionId(transactionId);
       if (incident == null){
            throw new RuntimeException("No open fraud incident found for the provided transaction ID");
       }
      var  prevSnapshot = AuditSnapshot.fromIncident(SnapshotKind.PREV, incident.getRiskScore());
      incident.changeIncidentStatus(FraudIncidentStatus.valueOf(status));
      logger.atInfo().log("Changed fraud incident status to: {} for transaction ID: {}", status, transactionId);
      incident.resolvedAtNow();
      
      AccountRiskProfile riskProfile= riskProfileRepository.findByAccountId(incident.getAccountId()).orElseThrow(() -> new RuntimeException("Account risk profile not found"));
     
      riskProfile.registerIncident(incident.getStatus(), incident.getOpenedAt());
     logger.atInfo().log("Registered incident summary to account risk profile for account ID: {}", incident.getAccountId().id());

     FraudIncident resolvedIncident = fraudIncidentRepository.resolve(incident);
      AuditReason auditReason= auditReasonPolicy.forgeFromIncidentStatus(resolvedIncident.getStatus());
       var nextSnapshot=  AuditSnapshot.fromIncident(SnapshotKind.NEXT,resolvedIncident.getRiskScore());
      auditAppender.append(AuditEntry.incidentResolved(
             resolvedIncident.getIncidentId(),
             resolvedIncident.getClientAccountId(),
             auditReason,
                 ActorType.USER,
                 prevSnapshot,
             nextSnapshot
             ));
       riskProfileService.update(riskProfile);
    logger.atInfo().log("Updated account risk profile for account ID: {}", incident.getAccountId().id());
  return mapToResponse(resolvedIncident);  
  }


    private IncidentResponseDto mapToResponse(FraudIncident resolvedIncident){
  return IncidentResponseDto.builder()
              .resolvedAt(resolvedIncident.getResolvedAt())
              .accountId(resolvedIncident.getAccountId().id())
              .riskScore(resolvedIncident.getRiskScore().value())
              .status(resolvedIncident.getStatus().name())
              .build();
    }


    @Override
    @Transactional
    public void updateStatus(FraudIncidentId incidentId, String status) {
 logger.atInfo().log("Starting fraud incident resolution process  {}", incidentId);
       FraudIncident incident=  fraudIncidentRepository.findByFraudIncidentId(incidentId);
       if (incident == null){
            throw new RuntimeException("No open fraud incident found for the provided transaction ID");
       }
      var  prevSnapshot = AuditSnapshot.fromIncident(SnapshotKind.PREV, incident.getRiskScore());

      incident.changeIncidentStatus(FraudIncidentStatus.valueOf(status));
      
      incident.lastUpdatedAtNow();
      
      AccountRiskProfile riskProfile= riskProfileRepository.findByAccountId(incident.getAccountId()).orElseThrow(() -> new RuntimeException("Account risk profile not found"));
     
      riskProfile.registerIncident(incident.getStatus(), incident.getOpenedAt());
     logger.atInfo().log("Registered incident summary to account risk profile for account ID: {}", incident.getAccountId().id());

     FraudIncident updatedIncident = fraudIncidentRepository.update(incidentId.id(), incident);

      AuditReason auditReason= auditReasonPolicy.forgeFromIncidentStatus(updatedIncident.getStatus());
     var nextSnapshot=  AuditSnapshot.fromIncident(SnapshotKind.NEXT,updatedIncident.getRiskScore());

    logger.atInfo().log("Appending audit log for incident ID: {} with new status: {}", incidentId, status);

     auditAppender.append(AuditEntry.incidentResolved(
             updatedIncident.getIncidentId(),
             updatedIncident.getClientAccountId(),
             auditReason,
                 ActorType.USER,
                 prevSnapshot,
             nextSnapshot
             ));

      riskProfileService.update(riskProfile);
    logger.atInfo().log("Updated account risk profile for account ID: {}", incident.getAccountId().id());

    }
}
