package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.decision.AuditEventDto;
import com.github.sentinel.pay.application.dto.decision.FraudDecisionDetailsDto;
import com.github.sentinel.pay.application.dto.incidents.IncidentDTO;
import com.github.sentinel.pay.application.dto.transaction.TransactionDetails;
import com.github.sentinel.pay.application.usecases.GetDecisionDetailByDecisionIdUseCase;
import com.github.sentinel.pay.domain.entity.audit.AuditLog;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.repository.AuditAppenderRepository;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.domain.repository.TransactionRepository;
 
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDecisionDetailByDecisionIdInteractor implements GetDecisionDetailByDecisionIdUseCase {
     private final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GetDecisionDetailByDecisionIdInteractor.class);
      private final FraudDecisionRepository fraudDecisionRepository;
      private final FraudIncidentRepository fraudIncidentRepository;
      private final AuditAppenderRepository auditAppenderRepository;
      private final TransactionRepository transactionRepository;

    @Override
    public FraudDecisionDetailsDto execute(FraudDecisionId fraudDecisionId) {
        logger.atInfo().log("Starting decision details retrieval process for decision ID: {}", fraudDecisionId);
       FraudDecision decision= fraudDecisionRepository.findById(fraudDecisionId.id()).orElseThrow(() -> new RuntimeException("decision not found"));
       List<AuditLog> auditLog=  auditAppenderRepository.getAuditsByEntityId(decision.getFraudDecisionId().id());
       List<FraudIncident> incidents=  fraudIncidentRepository.findIncidentsByDecisionId(decision.getFraudDecisionId());
       Transaction tx= transactionRepository.findById(decision.getTransactionId().id()).orElseThrow(()-> new RuntimeException("transaction not found"));
       List<IncidentDTO> incidentsDto = getIncidents(incidents);              
       List<AuditEventDto> auditEvents= getAuditEvents(auditLog);
       logger.atInfo().log("Retrieved decision, audit logs, and linked incidents for decision ID: {}", fraudDecisionId);
       
       logger.atInfo().log("Returning response DTO for decision ID: {}", fraudDecisionId);
       return buildResponse(decision, auditEvents, incidentsDto, tx);
 
    }

private List<IncidentDTO> getIncidents(List<FraudIncident> incidents){
  if (incidents.isEmpty()){
   logger.atInfo().log("No linked incidents found for the decision");
    return List.of();
  }
    return incidents.stream().map(i->
                                   IncidentDTO.builder().timestamp(i.getOpenedAt())
                                  .status(i.getStatus().name())
                                  .incidentId(i.getIncidentId().id())
                                  .build())
                                  .toList();
}

    private List<AuditEventDto> getAuditEvents(List<AuditLog> auditLog){
      if (auditLog.isEmpty()){
        logger.atInfo().log("No audit events found for the decision");
        return List.of();
      }
        return auditLog.stream().map(
            p-> AuditEventDto
            .builder()
            .action(p.getAction().name())
            .description(p.getAuditReason().Description())
            .actor(p.getActorType().name()) 
            .timestamp(p.getTimestamp())
            .build()
        ).toList();
    }
    private FraudDecisionDetailsDto buildResponse(FraudDecision decision, List<AuditEventDto> auditEvents, List<IncidentDTO> incidentsDto, Transaction tx){
        return FraudDecisionDetailsDto.builder()
        .decisionId(decision.getFraudDecisionId().id())
        .actor("System")
        .decisionType(decision.getFraudDecisionType().name())
        .description(decision.getDescription())
        .auditTimeline(auditEvents)
        .issuedAt(decision.getIssuedAt())//change to issueAT In the template
        .modifiedAt(decision.getModifiedAt())
        .linkedIncidents(incidentsDto)
        .transactionDetails(
        TransactionDetails.builder()
        .transactionId(tx.getTransactionId().id())
        .Type(tx.getTransactionType().name())
        .Amount(tx.getMoney().amount().intValue())
        .Currency(tx.getMoney().currency().name())
        .Location(tx.getLocation().toString())
        .TimeStamp(tx.getTimestamp())
        .build())
        .build();
    }
}
