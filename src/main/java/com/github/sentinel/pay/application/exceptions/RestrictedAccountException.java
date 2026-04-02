package com.github.sentinel.pay.application.exceptions;

import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;

public class RestrictedAccountException extends RuntimeException {
    private final FraudDecision decision;

    public RestrictedAccountException(FraudDecision decision) {
        super("Account is restricted");
        this.decision = decision;
    }

    public RestrictedAccountException(FraudDecision savedDecision, String string) {
        super(string);
        this.decision = savedDecision;
    }

    public FraudDecision getDecision() {
        return decision;
    }
    
}
