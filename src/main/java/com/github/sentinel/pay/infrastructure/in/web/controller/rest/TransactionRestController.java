package com.github.sentinel.pay.infrastructure.in.web.controller.rest;

import com.github.sentinel.pay.application.dto.transaction.TransactionEvaluationResponseDto;
import com.github.sentinel.pay.application.dto.transaction.TransactionRequestDto;
import com.github.sentinel.pay.application.services.EvaluateTransactionForFraudUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionRestController {
    private final EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase;

    @PostMapping("/evaluate")
    public ResponseEntity<TransactionEvaluationResponseDto> evaluateTransaction(@RequestBody TransactionRequestDto dto) {
        // For demonstration, we'll create a mock response.
        // In a real application, this would call the domain service.d
        var response = evaluateTransactionForFraudUseCase.evaluate(dto);
        return ResponseEntity.ok(response);
}
}
