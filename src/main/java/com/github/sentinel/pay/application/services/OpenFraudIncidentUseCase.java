package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public interface OpenFraudIncidentUseCase {
    FraudIncident openIncident(FraudDecision fraudDecision, Transaction tx);
}
