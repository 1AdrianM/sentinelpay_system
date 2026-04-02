package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;

public interface EscalateFraudIncidentUseCase {

    void execute(FraudIncidentId incident, String status);
    
}
