package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.services.FraudDecisionService;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionId;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecisionType;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class FraudDecisionServiceImpl implements FraudDecisionService {
        private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(FraudDecisionServiceImpl.class);       
    private final FraudDecisionRepository fraudDecisionRepository;

    @Override
    public void update(FraudDecisionId fraudDecisionId, FraudDecisionDto fraudDecisionDto) {
        logger.atInfo().log("Starting update process for fraud decision with ID: {}", fraudDecisionId.id());    
        FraudDecision fraudDecision= fraudDecisionRepository
             .findById(fraudDecisionId.id())
             .orElseThrow(()-> new RuntimeException("fraud decision with that ID not found"));
    logger.atInfo().log("Retrieved fraud decision with ID: {} for update", fraudDecisionId.id());
             fraudDecision.updateDecision(FraudDecisionType.valueOf(fraudDecisionDto.getDecisionType()), fraudDecisionDto.getDescription(), Instant.now());
    logger.atInfo().log("Updated fraud decision with ID: {} to new type: {}", fraudDecisionId.id(), fraudDecision.getFraudDecisionType().name());
     fraudDecisionRepository.update(fraudDecision.getFraudDecisionId().id(),fraudDecision);
     logger.atInfo().log("Persisted updated fraud decision with ID: {}", fraudDecisionId.id());
    }
    @Override
    public FraudDecision create(FraudDecision fraudDecision) {

      return  fraudDecisionRepository.save(fraudDecision);
     
    }
}
