package com.github.sentinel.pay.infrastructure.in.web.controller;

import com.github.sentinel.pay.application.services.EvaluateTransactionForFraudUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class TransactionController {
   private final EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase;

    public TransactionController(EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase) {
        this.evaluateTransactionForFraudUseCase = evaluateTransactionForFraudUseCase;
    }

    // Serves the main console page
    @GetMapping("/console")
    public String console(Model model) {
        // Pre-fill the text area with a sample JSON
        String sampleJson = """
                {
                    "transactionId": "%s",
                    "accountId": "%s",
                    "amount": 125.00,
                    "currency": "USD",
                    "location": "DO-SD",
                    "timestamp": "%s",
                    "channel": "CRYPTO",
                    "merchantType": "EXCHANGE"
                }""".formatted(UUID.randomUUID().toString(),UUID.randomUUID().toString(), Instant.now().toString());
        model.addAttribute("sampleJson", sampleJson);
        return "console";
    }

    // Record definitions for mock response
    record ProfileEvaluation(String profileName, int riskScore, String decision, List<String> rulesFired) {}
    record TransactionEvaluationResponse(
            String fraudDecision,
            int globalRiskScore,
            List<ProfileEvaluation> profileBreakdown,
            String incidentId
    ) {}


    // Handles the transaction evaluation request from HTMX
    @PostMapping("/transactions/evaluate")
    public String evaluateTransaction(@RequestBody(required = false)
                                          String jsonPayload,
                                          Model model) {
        // For demonstration, we'll create a mock response.
        // In a real application, this would call the domain service.

        // Mock logic: if amount is high, risk is high.
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
        );

        model.addAttribute("response", response);

        // Return the name of the fragment to be rendered
        return "fragments/transaction-response :: response-card";
    }
}