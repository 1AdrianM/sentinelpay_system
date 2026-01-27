package com.github.sentinel.pay.domain.entity.audit;

public record AuditReason(
        ReasonCode type,
        String Description
) {
}
