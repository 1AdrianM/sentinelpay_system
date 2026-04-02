package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.usecases.ListAllFraudIncidentsByTenantUseCase;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAllFraudIncidentsByTenantInteractor implements ListAllFraudIncidentsByTenantUseCase {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ListAllFraudIncidentsByTenantInteractor.class);
   private final FraudIncidentRepository fraudIncidentRepository;
    @Override
    public List<IncidentResponseDto> execute() {
        logger.atInfo().log("Starting retrieval of all fraud incidents for tenant");
        TenantContext tenantContext=  TenantContextHolder.get();
        ClientAccountId clientAccountId =  new  ClientAccountId(tenantContext.getClientAccountId());
logger.atInfo().log("Retrieved tenant context with client account ID: {}", clientAccountId);
        List<FraudIncident> incidentList= fraudIncidentRepository.findAllByClientAccountId(clientAccountId);
        logger.atInfo().log("Retrieved {} fraud incidents for client account ID: {}", incidentList.size(), clientAccountId);
        return  incidentList.stream()
             .map(i-> IncidentResponseDto.builder()
                            .transactionId(i.getTransactionId().id())
                             .incidentId(i.getIncidentId().id())
                             .status(i.getStatus().name())
                             .resolvedAt(i.getResolvedAt())
                             .accountId(i.getClientAccountId().id())
                             .riskScore(i.getRiskScore().value())
                             .openedAt(i.getOpenedAt())
                             .build()
                     ).toList();

    }
}
