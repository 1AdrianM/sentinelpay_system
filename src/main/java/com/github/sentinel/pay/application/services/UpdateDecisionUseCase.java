package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;

public interface UpdateDecisionUseCase {
    void execute(FraudDecisionId fraudDecisionId,FraudDecisionDto fraudDecisionDto);
}
