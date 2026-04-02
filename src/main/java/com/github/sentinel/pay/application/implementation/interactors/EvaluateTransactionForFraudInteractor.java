package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.riskProfile.ProfileEvaluationDto;
import com.github.sentinel.pay.application.dto.transaction.TransactionEvaluationResponseDto;
import com.github.sentinel.pay.application.dto.transaction.TransactionRequestDto;
import com.github.sentinel.pay.application.exceptions.RestrictedAccountException;
import com.github.sentinel.pay.application.mapper.ProfileBreakDownMapper;
import com.github.sentinel.pay.application.services.FraudDecisionService;
import com.github.sentinel.pay.application.services.FraudIncidentService;
import com.github.sentinel.pay.application.services.RiskProfileService;
import com.github.sentinel.pay.application.services.TransactionService;
import com.github.sentinel.pay.application.usecases.EvaluateTransactionForFraudUseCase;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
 import com.github.sentinel.pay.domain.entity.accountRiskProfile.RiskLevelPolicy;
import com.github.sentinel.pay.domain.entity.audit.*;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotKind;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.Money;
import com.github.sentinel.pay.domain.policies.AuditReasonPolicy;
import com.github.sentinel.pay.domain.policies.FraudDecisionPolicy;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import com.github.sentinel.pay.domain.entity.fraudRules.FraudRule;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;
import com.github.sentinel.pay.domain.entity.risk.RiskLevel;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.Channel;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionType;
import com.github.sentinel.pay.domain.repository.AuditAppenderRepository;

import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

 import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
//TODO RENOMBRAR USE CASES REUTILIZABLES A SERVICES, POR MOTIVOS DE CONCEPTO, LEGIBILIDAD Y ESCALABILIDAD
@RequiredArgsConstructor
@Service

public class EvaluateTransactionForFraudInteractor implements EvaluateTransactionForFraudUseCase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(EvaluateTransactionForFraudInteractor.class);
    private final List<FraudRule> fraudRules;
    private final FraudDecisionPolicy fraudDecisionPolicy;
    private final AuditReasonPolicy auditReasonPolicy;

      private final FraudIncidentService fraudIncidentService;
      private final RiskProfileService riskProfileService;
      private final FraudDecisionService fraudDecisionService;
      private final AuditAppenderRepository auditAppender;
      private final TransactionService transactionService;
          private final RiskLevelPolicy riskLevelPolicy;


    /**
     */
    //Se toman la transaction y se toma el perfil de riesgo, se analiza
// según historial de perfil y reglas de fraude para la transaction
@Transactional
public TransactionEvaluationResponseDto evaluate(TransactionRequestDto txDto){
  
    logger.atInfo().log("Starting transaction evaluation for fraud process");
  
    if(txDto == null){  
        throw new RuntimeException("transaction is null");
    }
  
    TenantContext tenantContext=  TenantContextHolder.get();
   var clientAccountId=  tenantContext.getClientAccountId();


   if(clientAccountId == null) {
   throw new RuntimeException("Client Account ID is null");
   }

     Instant now = Instant.now(); //time of receving transaction
     Transaction tx=
             Transaction.create(
             Transaction.generateTransactionId(),
             new ClientAccountId(clientAccountId),
             new AccountId(txDto.getAccountId()),
             Location.of(txDto.city, txDto.country),
             Money.of(txDto.amount,txDto.currency),
             TransactionType.valueOf(txDto.getTransactionType()),
             Channel.valueOf(txDto.getChannel()),
             now);

//--
logger.atInfo().log("Fetching or creating risk profile for account ID: {}", tx.getAccountId());
  // var riskProfile= riskProfileRepository.findOrCreateByAccountId(tx.getAccountId());

  AccountRiskProfile riskProfile= riskProfileService.findOrCreate(tx.getAccountId());

     
  CheckIfRiskProfileRestrictedRejectTransaction(riskProfile, tx, clientAccountId);


      riskProfile.registerTransactionData(tx);

   logger.atInfo().log("Evaluating transaction against fraud rules");
    List<FraudSignal> fraudSignals= fraudRules.stream()
    .map(rule -> rule.evaluateTransaction(tx, riskProfile)).collect(Collectors.toList());
    
    System.out.println("Fraud signals generated score : "+fraudSignals.stream().map(d->d.ruleTriggered()).collect(Collectors.toList()));
        //--guardamos una transaction
  Transaction savedTx=  transactionService.create(tx);

    //procedemos a calcular score de riesgo a partir de las reglas
    RiskScore score=  RiskScore.from(fraudSignals);

  String signalDescription = fraudSignals.stream().map(s-> s.description()).collect(Collectors.joining(", "));
    FraudDecisionType decisionType = fraudDecisionPolicy.decide(score);
    //creando decision a partir de policies
   logger.atInfo().log("Determined fraud decision type: {}", decisionType);

    var decision=  FraudDecision.create(
                     FraudDecision.generatefraudDecisionId(),
                     ClientAccountId.of(clientAccountId),
                    savedTx.getAccountId(),
                    Instant.now(),
                    savedTx.getTransactionId(), 
                     decisionType,
                     score, 
                     signalDescription);



  FraudDecision savedDecision= fraudDecisionService.create(decision);
//TODO

AuditReason auditReason= auditReasonPolicy.forgeFromDecisionType(savedDecision.getFraudDecisionType());

logger.atInfo().log("Determined audit reason for fraud decision: {}", auditReason);

    auditAppender.append(AuditEntry.decisionCreated(
            savedDecision.getFraudDecisionId(),
            savedDecision.getClientAccountId(),
                   auditReason,
                    ActorType.SYSTEM,
            AuditSnapshot.fromDecision(
                    SnapshotKind.NEXT,
                    savedDecision.getRiskPoint())
            ));
            logger.atInfo().log("Appended audit entry for fraud decision creation with decision ID: {}", savedDecision.getFraudDecisionId());
    //Apertura de Incidentes y guardado de estos

    FraudIncident savedFraudIncident=
            fraudIncidentService.open(savedDecision,savedTx);
            logger.atInfo().log("Fraud incident opened with ID: {}", savedFraudIncident.getIncidentId());
//TODO
    AuditReason auditIncidentReason=  auditReasonPolicy.forgeFromIncidentStatus(savedFraudIncident.getStatus());
    auditAppender.append(AuditEntry.incidentRaised(
            savedFraudIncident.getIncidentId(),
            savedFraudIncident.getClientAccountId(),
            auditIncidentReason,
            ActorType.SYSTEM,
             AuditSnapshot.fromIncident(SnapshotKind.NEXT,
                     savedFraudIncident.getRiskScore())
             )
    );

    //Actualizando perfil de riesgo y
    // eligiendo el risk level basado en los incidentes de la nueva transaction
    // riskLevelPolicy.
    //Registrando nuevo data de transaction dentro del riskProfile
    
    riskProfile.registerIncident(savedFraudIncident.getStatus(), savedFraudIncident.getOpenedAt());

    riskProfile.calculateAverageRiskScore(savedFraudIncident.getRiskScore());
    
    logger.atInfo().log("Updated risk level to: {} for account ID: {}", riskProfile.getRiskLevel().name(), riskProfile.getAccountId().id());

    riskProfile.updateRiskLevel(riskLevelPolicy.evaluate(riskProfile.getIncidents(), riskProfile.getAverageRiskScore()))
    ;
    riskProfileService.update(riskProfile);


    logger.atInfo().log("Updated risk profile for account ID: {}", savedTx.getAccountId());
    //TODO APPENDER FOR RISK PROFILE UPDATES
   
    return buildResponse(savedDecision, savedFraudIncident, createBreakDownList(fraudSignals));
    }





    private TransactionEvaluationResponseDto buildResponse(FraudDecision savedDecision, FraudIncident savedFraudIncident, List<ProfileEvaluationDto> breakDownList ){
     return TransactionEvaluationResponseDto.builder()
        .fraudDecision(savedDecision.getFraudDecisionType().name())
        .globalRiskScore(savedFraudIncident.getRiskScore().value())
        .incidentId(savedFraudIncident.getIncidentId().id().toString())
            .profileBreakdown(breakDownList)
        .build();
}

  

private void CheckIfRiskProfileRestrictedRejectTransaction(AccountRiskProfile riskProfile, Transaction tx, UUID clientAccountId) {
  if(riskProfile.getRiskLevel() == RiskLevel.RESTRICTED){
    logger.atInfo().log("Account ID: {} is currently restricted. Automatically flagging transaction as high risk.", tx.getAccountId());
   
    FraudDecisionType decisionType = FraudDecisionType.REJECTED;
   
    FraudDecision decision=  FraudDecision.create(
                     FraudDecision.generatefraudDecisionId(),
                     ClientAccountId.of(clientAccountId),
                     tx.getAccountId(),
                     Instant.now(),
                     tx.getTransactionId(), 
                     decisionType,
                     RiskScore.fromRejected(), 
                     "Automatically rejected due to restricted account status");

    FraudDecision savedDecision= fraudDecisionService.create(decision);

   auditAppender.append(AuditEntry.decisionCreated(
            savedDecision.getFraudDecisionId(),
            savedDecision.getClientAccountId(),
                   auditReasonPolicy.forgeFromDecisionType(decisionType),
                    ActorType.SYSTEM,
            AuditSnapshot.fromDecision(SnapshotKind.NEXT,
                    savedDecision.getRiskPoint())
   ));

    throw new RestrictedAccountException(savedDecision,"Transaction rejected due to restricted account status");

  }   

}
private List<ProfileEvaluationDto>createBreakDownList(List<FraudSignal> fraudSignals){
 List<ProfileEvaluationDto> breakDownList= new ArrayList<>();
    for(FraudSignal signal: fraudSignals) {
        breakDownList.add(ProfileBreakDownMapper.signalToProfileEvaluation(signal));
    }

    return breakDownList;
}

/*     */
}