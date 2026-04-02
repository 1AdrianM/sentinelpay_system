package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;

public interface FraudDecisionService {
    void update(FraudDecisionId fraudDecisionId,FraudDecisionDto fraudDecisionDto);
   FraudDecision create(FraudDecision fraudDecision);
}
