package com.github.sentinel.pay.domain.entity.audit;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;

import java.time.Instant;

public class AuditEntry {

     public static AuditLog  incidentRaised(FraudIncidentId incidentId, ClientAccountId clientAccountId,AuditReason reason, ActorType actor, AuditSnapshot initial){
      return new AuditLog(
             AuditLog.generateAuditLogId(),
             incidentId.id(),
             AuditEntityType.INCIDENT,
             reason,
             clientAccountId,
             actor,
             AuditAction.INCIDENT_RAISED,
             null,
             initial,
             Instant.now()
             );
       }
   public static AuditLog decisionCreated(FraudDecisionId decisionId, ClientAccountId clientAccountId, AuditReason reason, ActorType actor, AuditSnapshot initial) {

       return new AuditLog(
               AuditLog.generateAuditLogId(),
               decisionId.id(),
               AuditEntityType.DECISION,
               reason,
               clientAccountId,
               actor,
               AuditAction.DECISION_CREATED,
               null,
               initial,
               Instant.now()
       );
   }
   public static  AuditLog  decisionOverridden(FraudDecisionId decisionId, ClientAccountId clientAccountId,AuditReason reason, ActorType actor, AuditSnapshot prevSnapShot, AuditSnapshot nextSnapShot) {
       var now = Instant.now();
       return new AuditLog(
               AuditLog.generateAuditLogId(),
               decisionId.id(),
               AuditEntityType.DECISION,
               reason,
               clientAccountId,
               actor,
               AuditAction.DECISION_OVERRIDDEN,
               prevSnapShot,
               nextSnapShot,
               Instant.now()
       );
   }

   public static AuditLog incidentResolved(FraudIncidentId incidentId, ClientAccountId clientAccountId, AuditReason reason,ActorType actor, AuditSnapshot prevSnapShot, AuditSnapshot nextSnapShot) {
var now = Instant.now();
       return new AuditLog(
               AuditLog.generateAuditLogId(),
               incidentId.id(),
               AuditEntityType.INCIDENT,
               reason,
               clientAccountId,
               actor,
               AuditAction.INCIDENT_RESOLVED,
               prevSnapShot,
               nextSnapShot,
              Instant.now()
       );
   }

     public static AuditLog  transactionEvaluated (TransactionId transactionId, ClientAccountId clientAccountId,AuditReason reason,ActorType actor, AuditSnapshot initial){
        var now = Instant.now();
         return new AuditLog(
                 AuditLog.generateAuditLogId(),
                 transactionId.id(),
                 AuditEntityType.TRANSACTION,
                 reason,
                 clientAccountId,
                 actor,
                 AuditAction.TRANSACTION_EVALUATED,
                 null,
                 initial,
                 Instant.now()
         );
     }
}

