package com.github.sentinel.pay.application.implementation.interactors;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.services.FraudDecisionService;
import com.github.sentinel.pay.application.usecases.UpdateDecisionUseCase;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UpdateDecisionInteractor implements UpdateDecisionUseCase{
    private final FraudDecisionService decisionService;

    @Override
    public void execute(FraudDecisionId fraudDecisionId, FraudDecisionDto fraudDecisionDto) {
    
        decisionService.update(fraudDecisionId, fraudDecisionDto);
    }
    
}
