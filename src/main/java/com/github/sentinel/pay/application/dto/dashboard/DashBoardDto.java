package com.github.sentinel.pay.application.dto.dashboard;

import com.github.sentinel.pay.application.dto.decision.FraudDecisionDto;
import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.dto.riskProfile.RiskProfileDto;
import lombok.Builder;

import java.util.List;
@Builder
public class DashBoardDto {
      public  int totalTxPerDay;
       public int openIncidents;
      public   int confirmedFrauds;
      public   int highRestrictedAccounts;
          public   List<IncidentResponseDto> openFraudDtoList;
         public    List<RiskProfileDto> toFiveRiskProfileDtoList;
        public     List<FraudDecisionDto> latestFraudDecisionList;

}
