package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDetailsDto;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;


public interface GetDecisionDetailByDecisionIdUseCase {
     FraudDecisionDetailsDto execute(FraudDecisionId fraudDecisionId);
}
