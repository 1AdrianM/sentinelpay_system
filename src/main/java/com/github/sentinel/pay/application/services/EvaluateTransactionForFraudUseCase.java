package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.application.dto.transaction.TransactionEvaluationResponseDto;
import com.github.sentinel.pay.application.dto.transaction.TransactionRequestDto;

public interface EvaluateTransactionForFraudUseCase {
    TransactionEvaluationResponseDto evaluate(TransactionRequestDto tx);

    }
