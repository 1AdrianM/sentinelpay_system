package com.github.sentinel.pay.domain.entity.audit;
public enum AuditAction {
    INCIDENT_RAISED,
    INCIDENT_RESOLVED,
    INCIDENT_ESCALATED,
    DECISION_CREATED,
    TRANSACTION_EVALUATED, DECISION_OVERRIDDEN
}
