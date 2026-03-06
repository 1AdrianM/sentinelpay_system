package com.github.sentinel.pay.application.mapper;

import com.github.sentinel.pay.application.dto.riskProfile.ProfileEvaluationDto;
import com.github.sentinel.pay.domain.entity.risk.FraudSignal;

import java.util.List;

public class ProfileBreakDownMapper {

    public static ProfileEvaluationDto signalToProfileEvaluation(FraudSignal fraudSignals){
     return   ProfileEvaluationDto.builder()
                .profileName(fraudSignals.ruleTriggered())
                .rulesFired(fraudSignals.ruleTriggered())
                .riskScore(fraudSignals.score())
                .decision(fraudSignals.description())
             .build();
    }
}
