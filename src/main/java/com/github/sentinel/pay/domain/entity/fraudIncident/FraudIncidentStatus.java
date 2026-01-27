package com.github.sentinel.pay.domain.entity.fraudIncident;

public enum FraudIncidentStatus {
    OPEN,
    UNDER_REVIEW ,
    RESOLVED,
    CONFIRMED_FRAUD,
    FALSE_POSITIVE
}
