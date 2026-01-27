package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.dashboard.DashBoardDto;
import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.dto.riskProfile.RiskProfileDto;
import com.github.sentinel.pay.application.services.GetDashBoardDataUseCase;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import com.github.sentinel.pay.domain.repository.TransactionRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class GetDashBoardDataInteractor implements GetDashBoardDataUseCase {
    private final FraudIncidentRepository fraudIncidentRepository;
    private final TransactionRepository transactionRepository;
    private final RiskProfileRepository riskProfileRepository;
    private final FraudDecisionRepository fraudDecisionRepository;
    @Override
    public DashBoardDto execute() {
//TODO needs a refactor

        TenantContext tenantContext=  TenantContextHolder.get();
     ClientAccountId clientAccountId =  new  ClientAccountId(tenantContext.getClientAccountId());
        //hay que crear una query que nos cuenta el numero de transaciones un dia, solo la cuenta
        // el numero de incidentes abiertos
        // numero de fraudes confirmados
        //numero de cuentas restringidas o de riesgo alto
        //ultimos 5 perfiles de riesgo
        // ultimas decisiones de fraud
         int totalOfTransactionThisDay  =  transactionRepository.findTransactionCountThisDay(Instant.now(),clientAccountId);
         int confirmedFraudCount =   fraudIncidentRepository.findAllConfirmedFraudIncidentsCount(clientAccountId);
         int  openIncidentTotal =   fraudIncidentRepository.findOpenIncidentCount(clientAccountId);
          int   highAndRestrictedCount = riskProfileRepository.findByHighAndRestrictedAccountCount(clientAccountId);
         List<RiskProfileDto> riskProfileList = riskProfileRepository.findLastFiveRiskProfileAccounts(clientAccountId)
                 .stream()
                 .map(p-> RiskProfileDto.builder()
                         .riskProfileId(p.getRiskProfileId().id().toString())
                         .riskLevel(p.getRiskLevel().name())
                         .build())
                 .toList();
         List<FraudDecisionDto>  decisionList= fraudDecisionRepository.findLastTwoDecision(clientAccountId)
                 .stream()
                 .map(d-> FraudDecisionDto.builder()
                         .decisionId(d.getFraudDecisionId().id().toString())
                         .decisionType(d.getFraudDecisionType().name())
                         .accountId(d.getAccountId().id().toString())
                         .createdAt(d.getIssuedAt())
                         .build())
                 .toList();
         List<IncidentResponseDto> incidentList= fraudIncidentRepository.findAllConfirmedFraudIncidents(clientAccountId)
                 .stream()
                 .map(i-> IncidentResponseDto.builder()
                         .incidentId(i.getIncidentId().id())
                         .status(i.getStatus().name())
                         .resolvedAt(i.getResolvedAt())
                         .riskScore(i.getRiskScore().value())
                         .openedAt(i.getOpenedAt())
                         .accountId(i.getIncidentId().id())
                                 .build()
                         )
                 .toList();


         return DashBoardDto.builder()
                .totalTxPerDay(totalOfTransactionThisDay)//ready
                .openIncidents(openIncidentTotal)//ready
                .confirmedFrauds(confirmedFraudCount)//ready
                .highRestrictedAccounts(highAndRestrictedCount)
                .toFiveRiskProfileDtoList(riskProfileList)//ready just need conversion
                .latestFraudDecisionList(decisionList)// ready
                .openFraudDtoList(incidentList)//ready needs conversion
                .build();
    }
}
