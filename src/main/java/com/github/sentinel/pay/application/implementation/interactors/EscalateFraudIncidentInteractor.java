package com.github.sentinel.pay.application.implementation.interactors;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.services.FraudIncidentService;
import com.github.sentinel.pay.application.usecases.EscalateFraudIncidentUseCase;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EscalateFraudIncidentInteractor implements EscalateFraudIncidentUseCase {
     private final FraudIncidentService fraudIncidentService;
  
    @Override
    public void execute(FraudIncidentId incidentId, String status) {
 
     if(incidentId == null || incidentId.id().toString().isBlank()){
            throw new IllegalArgumentException("incident id is required");
        }

        if(status.isBlank()|| status.isEmpty()){
            throw new IllegalArgumentException("status is required");
        }
        fraudIncidentService.updateStatus(incidentId, status);
    }
    
}
