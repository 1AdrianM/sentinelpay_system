package com.github.sentinel.pay.application.dto.riskProfile;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public class RiskProfileDto {
   public  String riskProfileId;
   public String riskLevel;
   public  String behaviourSummary;
   public  List<IncidentResponseDto> incidentResponseDtoList;
}
