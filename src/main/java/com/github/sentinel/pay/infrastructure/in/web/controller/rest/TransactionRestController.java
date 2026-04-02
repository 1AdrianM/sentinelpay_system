package com.github.sentinel.pay.infrastructure.in.web.controller.rest;

import com.github.sentinel.pay.application.dto.transaction.TransactionEvaluationResponseDto;
import com.github.sentinel.pay.application.dto.transaction.TransactionRequestDto;
import com.github.sentinel.pay.application.usecases.EvaluateTransactionForFraudUseCase;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionRestController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TransactionRestController.class);
    private final EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase;

    @PostMapping("/evaluate")
    public ResponseEntity<TransactionEvaluationResponseDto> evaluateTransaction(@RequestBody TransactionRequestDto dto) {
        logger.atInfo().log("Received transaction evaluation request with payload: {}", dto);
        // For demonstration, we'll create a mock response.
        // In a real application, this would call the domain service.d
        var response = evaluateTransactionForFraudUseCase.evaluate(dto);
        logger.atInfo().log("Transaction evaluation completed with response: {}", response);
        return ResponseEntity.ok(response);
}
}
