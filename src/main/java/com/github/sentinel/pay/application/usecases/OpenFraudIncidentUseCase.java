package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.incidents.OpenIncidentRequestDto;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;

public interface OpenFraudIncidentUseCase {

    FraudIncident execute(OpenIncidentRequestDto incidentRequestDto);
    
}
