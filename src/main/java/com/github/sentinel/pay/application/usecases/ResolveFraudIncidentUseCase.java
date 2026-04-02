package com.github.sentinel.pay.application.usecases;

import com.github.sentinel.pay.domain.entity.transaction.TransactionId;

public interface ResolveFraudIncidentUseCase {

    void execute(TransactionId transactionId, String name);
    
}
