package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDetailsDto;
import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.services.GetDecisionDetailByDecisionIdUseCase;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDecisionDetailByDecisionIdInteractor implements GetDecisionDetailByDecisionIdUseCase {
    @Override
    public FraudDecisionDetailsDto execute(FraudDecisionId fraudDecisionId) {
        return null;
    }
}
