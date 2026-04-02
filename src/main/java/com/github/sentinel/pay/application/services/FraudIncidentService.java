package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncident;
import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentId;
import com.github.sentinel.pay.application.dto.incidents.IncidentResponseDto;
import com.github.sentinel.pay.domain.entity.fraudDecision.FraudDecision;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;

public interface FraudIncidentService {
     FraudIncident open(FraudDecision fraudDecision, Transaction tx);
     IncidentResponseDto resolve(TransactionId transactionId, String status);
     void updateStatus(FraudIncidentId incidentId, String status);

}
