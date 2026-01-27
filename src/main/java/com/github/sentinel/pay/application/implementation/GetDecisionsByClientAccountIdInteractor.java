package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.services.GetDecisionsByClientAccountIdUseCase;
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
    private final FraudDecisionRepository fraudDecisionRepository;
    @Override
    public List<FraudDecisionDto> execute(Pageable pageable) {
       TenantContext tenantContext= TenantContextHolder.get();

     ClientAccountId clientAccountId= new ClientAccountId(tenantContext
             .getClientAccountId());
    List<FraudDecision> fraudIncidents=  fraudDecisionRepository.findAllByClientAccountId(clientAccountId,pageable);

    return fraudIncidents
            .stream()
            .map(d-> FraudDecisionDto.builder()
                    .decisionId(d.getFraudDecisionId().id().toString())
                    .decisionType(d.getFraudDecisionType().name())
                            .accountId(d.getAccountId().id().toString())
                            .createdAt(d.getIssuedAt())
                            .description(d.getDescription())
                    .build())
                    .collect(Collectors.toList());
    }
}
