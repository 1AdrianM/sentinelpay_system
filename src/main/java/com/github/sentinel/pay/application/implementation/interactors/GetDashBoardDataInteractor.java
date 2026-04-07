package com.github.sentinel.pay.application.implementation.interactors;

import com.github.sentinel.pay.application.dto.dashboard.DashBoardDto;
import com.github.sentinel.pay.application.dto.dashboard.SystemStatusDto;
import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.dto.riskProfile.RiskProfileDto;
import com.github.sentinel.pay.application.usecases.GetDashBoardDataUseCase;
import com.github.sentinel.pay.application.usecases.GetSystemStatusUseCase;
import com.github.sentinel.pay.domain.entity.shared.ClientAccountId;
import com.github.sentinel.pay.domain.repository.FraudDecisionRepository;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import com.github.sentinel.pay.domain.repository.TransactionRepository;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContext;
import com.github.sentinel.pay.infrastructure.config.security.tenant.TenantContextHolder;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties.Web.Client;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GetDashBoardDataInteractor implements GetDashBoardDataUseCase {
        private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GetDashBoardDataInteractor.class);
    private final FraudIncidentRepository fraudIncidentRepository;
    private final TransactionRepository transactionRepository;
    private final RiskProfileRepository riskProfileRepository;
    private final FraudDecisionRepository fraudDecisionRepository;
    private final GetSystemStatusUseCase getSystemStatusUseCase;

    @Override
    public DashBoardDto execute() {

        logger.atInfo().log("Starting dashboard data retrieval process");
       
        TenantContext tenantContext=  TenantContextHolder.get();
       
        ClientAccountId clientAccountId =  new  ClientAccountId(tenantContext.getClientAccountId());
       
        logger.atInfo().log("Retrieving total transaction count for today for client account ID: {}", clientAccountId);
     
         int totalOfTransactionThisDay  =  transactionRepository.findTransactionCountThisDay(Instant.now(),clientAccountId);
        
         int confirmedFraudCount =   fraudIncidentRepository.findAllConfirmedFraudIncidentsCount(clientAccountId);
        
         int  openIncidentTotal =   fraudIncidentRepository.findOpenIncidentCount(clientAccountId);
       
         int   highAndRestrictedCount = riskProfileRepository.findByHighAndRestrictedAccountCount(clientAccountId);

          logger.atInfo().log("Retrieved counts - Total transactions today: {}, Confirmed frauds: {}, Open incidents: {}, High/Restricted accounts: {}",
                  totalOfTransactionThisDay, confirmedFraudCount, openIncidentTotal, highAndRestrictedCount);
        
                 logger.atInfo().log("Retrieved last five risk profiles for client account ID: {}", clientAccountId);
         

        List<RiskProfileDto> riskProfileList = getRiskProfileList(clientAccountId);

                        logger.atInfo().log("Retrieved last two fraud decisions for client account ID: {}", clientAccountId);
         List<FraudDecisionDto> decisionList= getDecisionList(clientAccountId);
                        
         List<IncidentResponseDto> incidentList= getIncidentList(clientAccountId);

         SystemStatusDto systemStatus = getSystemStatusUseCase.execute();

         logger.atInfo().log("Assembling dashboard data transfer object for client account ID: {}", clientAccountId);
       
         return buildResponse(totalOfTransactionThisDay, confirmedFraudCount, openIncidentTotal, highAndRestrictedCount, riskProfileList, decisionList, incidentList, systemStatus);
    }


private List<RiskProfileDto> getRiskProfileList(ClientAccountId clientAccountId)
{
         return riskProfileRepository.findLastFiveRiskProfileAccounts(clientAccountId)
                 .stream()
                 .map(p-> RiskProfileDto.builder()
                         .riskProfileId(p.getRiskProfileId().id().toString())
                         .riskLevel(p.getRiskLevel().name())
                         .build())
                 .toList();
}
private List<FraudDecisionDto> getDecisionList(ClientAccountId clientAccountId){
 return fraudDecisionRepository.findLastTwoDecision(clientAccountId)
                 .stream()
                 .map(d-> FraudDecisionDto.builder()
                         .decisionId(d.getFraudDecisionId().id().toString())
                         .decisionType(d.getFraudDecisionType().name())
                         .accountId(d.getAccountId().id().toString())
                         .createdAt(d.getIssuedAt())
                         
                         .build())
                 .toList();
        }
  private List<IncidentResponseDto>  getIncidentList(ClientAccountId clientAccountId){
        return fraudIncidentRepository.findAllOpenFraudIncidents(clientAccountId)
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
    }

private DashBoardDto  buildResponse(int totalOfTransactionThisDay, int confirmedFraudCount, int openIncidentTotal, int highAndRestrictedCount, List<RiskProfileDto> riskProfileList, List<FraudDecisionDto> decisionList, List<IncidentResponseDto> incidentList, SystemStatusDto systemStatus){
       
       return DashBoardDto.builder()
                .totalTxPerDay(totalOfTransactionThisDay)//ready
                .openIncidents(openIncidentTotal)//ready
                .confirmedFrauds(confirmedFraudCount)//ready
                .highRestrictedAccounts(highAndRestrictedCount)
                .toFiveRiskProfileDtoList(riskProfileList)//ready just need conversion
                .latestFraudDecisionList(decisionList)// ready
                .openFraudDtoList(incidentList)//ready needs conversion
                .systemStatus(systemStatus)
                .build();
}
}
