package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;

import java.util.List;

public interface ListAllFraudIncidentsByTenantUseCase {
   List<IncidentResponseDto> execute();
}
