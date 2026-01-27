package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.services.ListAllFraudIncidentsByTenantUseCase;
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
   private final FraudIncidentRepository fraudIncidentRepository;
    @Override
    public List<IncidentResponseDto> execute() {
        TenantContext tenantContext=  TenantContextHolder.get();
        System.out.println("Tenant: "+ tenantContext);
        ClientAccountId clientAccountId =  new  ClientAccountId(tenantContext.getClientAccountId());

        List<FraudIncident> incidentList= fraudIncidentRepository.findAllByClientAccountId(clientAccountId);
        return  incidentList.stream()
             .map(i-> IncidentResponseDto.builder()
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
