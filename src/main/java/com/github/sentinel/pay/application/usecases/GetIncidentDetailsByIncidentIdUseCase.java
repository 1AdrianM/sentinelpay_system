package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.application.dto.incidents.IncidentDetails;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;

public interface GetIncidentDetailsByIncidentIdUseCase {
 IncidentDetails execute(FraudIncidentId incidentId);
}
