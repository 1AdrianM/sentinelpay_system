package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDetailsDto;
import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GetDecisionDetailByDecisionIdUseCase {
     FraudDecisionDetailsDto execute(FraudDecisionId fraudDecisionId);
}
