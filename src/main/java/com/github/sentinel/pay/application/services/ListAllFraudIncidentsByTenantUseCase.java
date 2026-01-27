package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;

import java.util.List;

public interface ListAllFraudIncidentsByTenantUseCase {
   List<IncidentResponseDto> execute();
}
