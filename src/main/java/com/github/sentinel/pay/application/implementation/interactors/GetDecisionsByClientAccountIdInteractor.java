package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.usecases.GetDecisionsByClientAccountIdUseCase;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDecisionsByClientAccountIdInteractor implements GetDecisionsByClientAccountIdUseCase {
        private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GetDecisionsByClientAccountIdInteractor.class);
    private final FraudDecisionRepository fraudDecisionRepository;
    @Override
    public List<FraudDecisionDto> execute(Pageable pageable) {
        logger.atInfo().log("Starting retrieval of fraud decisions for client account with pagination - Page number: {}, Page size: {}", pageable.getPageNumber(), pageable.getPageSize());
       TenantContext tenantContext= TenantContextHolder.get();

     ClientAccountId clientAccountId= new ClientAccountId(tenantContext
             .getClientAccountId());

             logger.atInfo().log("Retrieved tenant context with client account ID: {}", clientAccountId);
    List<FraudDecision> fraudDecisions=  fraudDecisionRepository.findAllByClientAccountId(clientAccountId,pageable);
logger.atInfo().log("Retrieved {} fraud decisions for client account ID: {}", fraudDecisions.size(), clientAccountId);

return fraudDecisions
            .stream()
            .map(d-> FraudDecisionDto.builder()
            .status(d.getFraudDecisionType().name())
            //.riskLevel(d.)
            .actor("System")
                    .decisionId(d.getFraudDecisionId().id().toString())
                    .decisionType(d.getFraudDecisionType().name())
                            .accountId(d.getAccountId().id().toString())
                            .createdAt(d.getIssuedAt())
                            .description(d.getDescription())
                    .build())
                    .collect(Collectors.toList());
    }
}
