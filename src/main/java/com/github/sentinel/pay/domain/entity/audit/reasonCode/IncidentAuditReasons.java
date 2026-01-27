package com.github.sentinel.pay.domain.entity.audit.reasonCode;

import com.github.sentinel.pay.domain.entity.audit.ReasonCode;

public enum IncidentAuditReasons implements ReasonCode {
    RULE_MATCH,
    RISK_THRESHOLD_EXCEEDED,
    MANUAL_OVERRIDE,
    FRAUD_PATTERN_DETECTED,
    COMPLIANCE_REQUIREMENT,
    SYSTEM_POLICY;


    @Override
    public String code() {
        return this.name();
    }
}
