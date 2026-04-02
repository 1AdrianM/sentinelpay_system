package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;

import java.util.List;
import java.util.UUID;

public interface ListFraudIncidentsByAccountUseCase {
 List<IncidentResponseDto> incidentsByAccount(UUID id);
}
