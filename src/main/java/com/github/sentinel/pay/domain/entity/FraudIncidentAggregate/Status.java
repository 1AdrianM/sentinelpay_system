package com.github.sentinel.pay.domain.entity.FraudIncidentAggregate;

public enum Status {
    OPEN,
    UNDER_REVIEW ,
    RESOLVED,
    CONFIRMED_FRAUD,
    FALSE_POSITIVE
}
