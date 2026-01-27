package com.github.sentinel.pay.domain.entity.audit.reasonCode;

import com.github.sentinel.pay.domain.entity.audit.ReasonCode;

public enum DecisionAuditReasons implements ReasonCode {
    MANUAL_REVIEW,
    AUTO_APPROVAL,
    AUTO_REJECTION;

    @Override
    public String code() {
        return this.name();
    }
}
