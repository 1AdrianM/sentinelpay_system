package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.transaction.TransactionRequestDto;
import com.github.sentinel.pay.application.services.EvaluateTransactionForFraudUseCase;
import com.github.sentinel.pay.application.services.OpenFraudIncidentUseCase;
import com.github.sentinel.pay.application.services.UpdateAccountRiskProfileUseCase;
import com.github.sentinel.pay.domain.entity.audit.*;
import com.github.sentinel.pay.domain.entity.audit.snapshots.SnapshotKind;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.policies.AuditReasonPolicy;
import com.github.sentinel.pay.domain.policies.FraudDecisionPolicy;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import com.github.sentinel.pay.domain.entity.fraudRules.FraudRule;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import com.github.sentinel.pay.domain.entity.risk.RiskScore;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.Channel;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionType;
import com.github.sentinel.pay.domain.repository.AuditAppenderRepository;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import com.github.sentinel.pay.domain.repository.TransactionRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EvaluateTransactionForFraudInteractor implements EvaluateTransactionForFraudUseCase {
    private final List<FraudRule> fraudRules;
    private final FraudDecisionPolicy fraudDecisionPolicy;
    private final AuditReasonPolicy auditReasonPolicy;

    private final FraudDecisionRepository fraudDecisionRepository;
    private final TransactionRepository transactionRepository;
    private final RiskProfileRepository riskProfileRepository;
    private final OpenFraudIncidentUseCase openFraudIncidentUseCase;
    private final UpdateAccountRiskProfileUseCase updateAccountRiskProfileUseCase;
    private final AuditAppenderRepository auditAppender;
    /**
     */
    //Se toman la transaction y se toma el perfil de riesgo, se analiza
// según historial de perfil y reglas de fraude para la transaction
public void evaluate( TransactionRequestDto txDto){
    if(txDto == null){
        throw new RuntimeException("transaction is null");
    }
 TenantContext tenantContext=  TenantContextHolder.get();
   var clientAccountId=  tenantContext.getClientAccountId();

     Instant now = Instant.now(); //time of receving transaction

     Transaction tx=
             Transaction.create(
             Transaction.generateTransactionId(),
             new ClientAccountId(clientAccountId),
                     FraudIncident.generateIncidentId()
                     ,new AccountId(txDto.getAccountId())
             ,txDto.getLocation()
            ,txDto.getMoney()
            ,TransactionType.valueOf(txDto.getTransactionType())
            ,Channel.valueOf(txDto.getChannel())
            ,now);

//--
   var riskProfile= riskProfileRepository
                    .findOrCreateByAccountId(tx.getAccountId());

    List<RiskContribution> contribution=
                          fraudRules.stream()
                                       .map(fraudRule -> fraudRule
                                               .evaluateTransaction(tx, riskProfile))
                                                                                    .toList();
    //--guardamos una transaction
  Transaction savedTx=  transactionRepository.save(tx);
    //procedemos a calcular score de riesgo a partir de las reglas
    RiskScore score=  RiskScore.from(contribution);


    FraudDecisionType decisionType = fraudDecisionPolicy.decide(score);
    //creando decision a partir de policies

    var decision=  FraudDecision.create(
                     FraudDecision.generatefraudDecisionId(),
                    new ClientAccountId(clientAccountId),
                    savedTx.getAccountId(),
                    Instant.now(),
                    savedTx.getTransactionId(),
                     decisionType,
                     score);
    FraudDecision savedDecision=fraudDecisionRepository.save(decision);

    AuditReason auditReason= auditReasonPolicy.forgeFromDecisionType(savedDecision.getFraudDecisionType());


    auditAppender.append(AuditEntry.decisionCreated(
            savedDecision.getFraudDecisionId(),
            savedDecision.getClientAccountId(),
                   auditReason,
                    ActorType.SYSTEM,
            AuditSnapshot.fromDecision(
                    SnapshotKind.NEXT,
                    savedDecision.getRiskPoint())
            ));
    //Apertura de Incidentes y guardado de estos

    FraudIncident savedFraudIncident=
            openFraudIncidentUseCase.openIncident(savedDecision,savedTx);
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
    riskProfile.registerTransactionData(savedTx);
    updateAccountRiskProfileUseCase.updateRiskProfile(riskProfile);
    //TODO APPENDER FOR RISK PROFILE UPDATES

}
}
