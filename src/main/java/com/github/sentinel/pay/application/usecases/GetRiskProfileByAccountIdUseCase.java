package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.riskProfile.RiskProfileDto;

public interface GetRiskProfileByAccountIdUseCase {
    RiskProfileDto execute(String accountId);
}
