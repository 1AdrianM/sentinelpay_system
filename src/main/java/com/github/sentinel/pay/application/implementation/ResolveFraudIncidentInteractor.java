package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.services.ResolveFraudIncidentUseCase;
import com.github.sentinel.pay.application.services.UpdateAccountRiskProfileUseCase;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.IncidentStatistics;
import com.github.sentinel.pay.domain.entity.audit.ActorType;
import com.github.sentinel.pay.domain.entity.audit.AuditEntry;
import com.github.sentinel.pay.domain.entity.audit.AuditReason;
import com.github.sentinel.pay.domain.entity.audit.AuditSnapshot;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotKind;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;
import com.github.sentinel.pay.domain.policies.AuditReasonPolicy;
import com.github.sentinel.pay.domain.repository.AuditAppenderRepository;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ResolveFraudIncidentInteractor implements ResolveFraudIncidentUseCase {
   private final FraudIncidentRepository fraudIncidentRepository;
   private final RiskProfileRepository riskProfileRepository;
   private final UpdateAccountRiskProfileUseCase updateAccountRiskProfileUseCase;
   private final AuditAppenderRepository auditAppender;
   private final AuditReasonPolicy auditReasonPolicy;
    @Override
    public IncidentResponseDto resolveFraudIncident(TransactionId transactionId, String status) {
      FraudIncident incident=  fraudIncidentRepository.findOpenByTransactionId(transactionId);
     var  prevSnapshot = AuditSnapshot.fromIncident(SnapshotKind.PREV, incident.getRiskScore());
      incident.changeIncidentStatus(FraudIncidentStatus.valueOf(status));
      incident.resolvedAtNow();

      var riskProfile= riskProfileRepository.findOrCreateByAccountId(incident.getAccountId());
      List<FraudIncidentStatus> incidentStatus= new ArrayList<>();
      //TODO
        //CHECK
                incidentStatus.add(incident.getStatus());
      IncidentStatistics summary= new IncidentStatistics(incidentStatus,incident.getOpenedAt());
      riskProfile.registerIncidentSummary(summary);


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
      updateAccountRiskProfileUseCase.updateRiskProfile(riskProfile);

      return IncidentResponseDto.builder()
              .resolvedAt(resolvedIncident.getResolvedAt())
              .accountId(resolvedIncident.getAccountId().id())
              .riskScore(resolvedIncident.getRiskScore().value())
              .status(resolvedIncident.getStatus().name())
              .build();

    }
}
