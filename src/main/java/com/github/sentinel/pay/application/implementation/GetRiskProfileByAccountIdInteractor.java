package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.dto.riskProfile.RiskProfileDto;
import com.github.sentinel.pay.application.services.GetRiskProfileByAccountIdUseCase;
import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.shared.AccountId;
import com.github.sentinel.pay.domain.repository.FraudIncidentRepository;
import com.github.sentinel.pay.domain.repository.RiskProfileRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class GetRiskProfileByAccountIdInteractor implements GetRiskProfileByAccountIdUseCase {
   private final RiskProfileRepository riskProfileRepository;
   private final FraudIncidentRepository fraudIncidentRepository;

    public GetRiskProfileByAccountIdInteractor(RiskProfileRepository riskProfileRepository, FraudIncidentRepository fraudIncidentRepository) {
        this.riskProfileRepository = riskProfileRepository;
        this.fraudIncidentRepository = fraudIncidentRepository;
    }

    @Override
    public RiskProfileDto execute(String accountId) {
        if (accountId.isEmpty() || accountId.isBlank()){
            throw new RuntimeException("accountId parameter found to be empty");
        }
    AccountId accountID =  new AccountId(UUID.fromString(accountId));
        AccountRiskProfile riskProfile =riskProfileRepository
                .findByAccountId(accountID);

      List<FraudIncident> incidentList= fraudIncidentRepository.findAllByAccountId(accountID, PageRequest.of(0,10));
        return RiskProfileDto.builder()
                .riskProfileId(riskProfile.getRiskProfileId().id().toString())
                        .behaviourSummary("SUMMARY LO HACEMOS HORITA")
                                .incidentResponseDtoList(incidentList.stream().map(i->IncidentResponseDto.builder()
                                        .incidentId(i.getIncidentId().id())
                                        .status(i.getStatus().toString())
                                        .openedAt(i.getOpenedAt())
                                        .riskScore(i.getRiskScore().value())
                                        .resolvedAt(i.getResolvedAt())
                                        .build()).toList())
                .build();

    }
}
