package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;

public interface ResolveFraudIncidentUseCase {

  IncidentResponseDto resolveFraudIncident(TransactionId transactionId, String status);
}
