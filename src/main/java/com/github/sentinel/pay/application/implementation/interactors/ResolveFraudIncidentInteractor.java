package com.github.sentinel.pay.application.implementation.interactors;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.services.FraudIncidentService;
import com.github.sentinel.pay.application.usecases.ResolveFraudIncidentUseCase;
import com.github.sentinel.pay.domain.entity.transaction.TransactionId;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ResolveFraudIncidentInteractor implements ResolveFraudIncidentUseCase {
     private final FraudIncidentService fraudIncidentService;

    @Override
    public void execute(TransactionId transactionId, String status) {
        fraudIncidentService.resolve(transactionId, status);
    }
    
}
