package com.github.sentinel.pay.application.dto.riskProfile;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public class RiskProfileDto {
    String riskProfileId;
    String riskLevel;
    String behaviourSummary;
    List<IncidentResponseDto> incidentResponseDtoList;
}
