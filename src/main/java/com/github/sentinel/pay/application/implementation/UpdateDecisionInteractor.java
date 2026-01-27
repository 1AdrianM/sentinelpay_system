package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.services.UpdateDecisionUseCase;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UpdateDecisionInteractor implements UpdateDecisionUseCase {
    private final FraudDecisionRepository fraudDecisionRepository;
    @Override
    public void execute(FraudDecisionId fraudDecisionId, FraudDecisionDto fraudDecisionDto) {
     FraudDecision fraudDecision= fraudDecisionRepository
             .findById(fraudDecisionId.id())
             .orElseThrow(()-> new RuntimeException("fraud decision with that ID not found"));
     fraudDecision
             .updateDecision(FraudDecisionType.valueOf(fraudDecisionDto.getDecisionType()), fraudDecisionDto.getDescription(), Instant.now());
     fraudDecisionRepository
             .update(fraudDecision.getFraudDecisionId().id(),fraudDecision);
    }
}
