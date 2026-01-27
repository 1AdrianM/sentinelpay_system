package com.github.sentinel.pay.application.implementation;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.application.services.ListFraudIncidentsByAccountUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ListFraudIncidentsByAccountInteractor implements ListFraudIncidentsByAccountUseCase {
    @Override
    public List<IncidentResponseDto> incidentsByAccount(UUID id) {
        return List.of();
    }
}
