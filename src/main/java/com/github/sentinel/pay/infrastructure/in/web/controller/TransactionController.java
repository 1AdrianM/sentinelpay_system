package com.github.sentinel.pay.infrastructure.in.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sentinel.pay.application.dto.transaction.TransactionRequestDto;
import com.github.sentinel.pay.application.services.EvaluateTransactionForFraudUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class TransactionController {
   private final EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase;
   private final ObjectMapper objectMapper;

    public TransactionController(EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase, ObjectMapper objectMapper) {
        this.evaluateTransactionForFraudUseCase = evaluateTransactionForFraudUseCase;
        this.objectMapper = objectMapper;
    }

    // Serves the main console page
    @GetMapping("/console")
    public String console(Model model) {
        // Pre-fill the text area with a sample JSON
        String sampleJson = """
                {
                    "accountId": "%s",
                    "amount": 125.00,
                    "currency": "USD",
                    "city": "DO",
                    "country":"SD",
                    "timestamp": "%s",
                    "channel": "PHONE",
                    "transactionType": "BILL_PAYMENT"
                }""".formatted(UUID.randomUUID().toString(), Instant.now().toString());
        model.addAttribute("sampleJson", sampleJson);
        return "console";
    }

    // Record definitions for mock response


    // Handles the transaction evaluation request from HTMX
    @PostMapping("/transactions/evaluate")
    public String evaluateTransaction(@RequestParam("jsonPayload")
                                          String jsonPayload,
                                          Model model) {
        // For demonstration, we'll create a mock response.
        // In a real application, this would call the domain service.
        try {
            TransactionRequestDto transactionRequestDto = objectMapper.readValue(jsonPayload, TransactionRequestDto.class);
            var response = evaluateTransactionForFraudUseCase.evaluate(transactionRequestDto);
            model.addAttribute("response", response);
            model.addAttribute("success", true);

        }catch(JsonProcessingException e) {
            model.addAttribute("error", "JSON inválido: " + e.getMessage());
            model.addAttribute("success", false);

        }
       // Mock logic: if amount is high, risk is high.
        /*
        boolean isHighRisk = jsonPayload != null && jsonPayload.contains("amount: 125.00");

        List<ProfileEvaluation> profileBreakdown = List.of(
                new ProfileEvaluation("MonetaryProfile", isHighRisk ? 80 : 20, isHighRisk ? "HIGH_RISK" : "OK", List.of("Amount > 100")),
                new ProfileEvaluation("LocationProfile", 15, "OK", List.of()),
                new ProfileEvaluation("VelocityProfile", 30, "OK", List.of()),
                new ProfileEvaluation("CurrencyProfile", 5, "OK", List.of())
        );

        TransactionEvaluationResponse response = new TransactionEvaluationResponse(
                isHighRisk ? "REVIEW" : "APPROVED",
                isHighRisk ? 85 : 35,
                profileBreakdown,
                isHighRisk ?"INC-" + UUID.randomUUID().toString().substring(0, 8) : null
        );*/
//TODO
        model.addAttribute("sampleJson", jsonPayload);
        // Return the name of the fragment to be rendered
        return "fragments/transaction-response :: response-card";
    }
}