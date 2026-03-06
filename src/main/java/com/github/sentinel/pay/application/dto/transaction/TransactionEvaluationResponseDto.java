package com.github.sentinel.pay.application.dto.transaction;

import com.github.sentinel.pay.application.dto.riskProfile.ProfileEvaluationDto;
import lombok.Builder;

import java.util.List;
@Builder
public class TransactionEvaluationResponseDto {
    public String fraudDecision;
    public int globalRiskScore;
    public List<ProfileEvaluationDto> profileBreakdown;
 public  String incidentId;
}
