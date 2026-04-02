package com.github.sentinel.pay.infrastructure.in.web.controller.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.sentinel.pay.application.dto.transaction.TransactionRequestDto;
import com.github.sentinel.pay.application.usecases.EvaluateTransactionForFraudUseCase;
import com.github.sentinel.pay.infrastructure.utils.TransactionDataGenerator;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@Controller
public class TransactionController {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TransactionController.class);
   private final EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase;
   private final ObjectMapper objectMapper;
    private static final String SESSION_ACCOUNT_KEY = "demoAccountId";

    public TransactionController(EvaluateTransactionForFraudUseCase evaluateTransactionForFraudUseCase, ObjectMapper objectMapper) {
        this.evaluateTransactionForFraudUseCase = evaluateTransactionForFraudUseCase;
        this.objectMapper = objectMapper;
    }

@GetMapping("/console")
    public String console(
            Model model, 
            HttpSession session,
            @RequestParam(required = false, defaultValue = "NORMAL") String profile) {
        
        // Get or create a session-specific demo account ID
        String accountId = getOrCreateSessionAccount(session);
        
        // Generate sample JSON based on profile
        String sampleJson = generateSampleJson(accountId, profile);
        
        model.addAttribute("sampleJson", sampleJson);
        model.addAttribute("sessionAccountId", accountId);
        model.addAttribute("currentProfile", profile);
        
        return "console";
    }
    /**
 
    /**
     * Generates a new random transaction using the utility class
     */
    @GetMapping("/console/generate")
    public String generateRandomTransaction(
            HttpSession session,
            @RequestParam(required = false, defaultValue = "RANDOM") String profile) {
        
        String accountId = getOrCreateSessionAccount(session);
        return "redirect:/console?profile=" + profile;
    }
 
    /**
     * Gets or creates a session account ID
     */
    private String getOrCreateSessionAccount(HttpSession session) {
        String accountId = (String) session.getAttribute(SESSION_ACCOUNT_KEY);
        
        if (accountId == null) {
            accountId =  UUID.randomUUID().toString();
            session.setAttribute(SESSION_ACCOUNT_KEY, accountId);
        }
        
        return accountId;
    }
 
    /**
     * Generates sample JSON based on the selected profile
     */
    private String generateSampleJson(String accountId, String profile) {
        try {
            TransactionDataGenerator.TransactionProfile transactionProfile = 
                    TransactionDataGenerator.TransactionProfile.valueOf(profile.toUpperCase());
            
            return TransactionDataGenerator.generateTransactionJson(accountId, transactionProfile);
        } catch (IllegalArgumentException e) {
            // Fallback to normal profile if invalid profile provided
            return TransactionDataGenerator.generateTransactionJson(
                    accountId, 
                    TransactionDataGenerator.TransactionProfile.NORMAL
            );
        }
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
            logger.atInfo().log("Received transaction evaluation request with payload: {}", jsonPayload);
            TransactionRequestDto transactionRequestDto = objectMapper.readValue(jsonPayload, TransactionRequestDto.class);
            var response = evaluateTransactionForFraudUseCase.evaluate(transactionRequestDto);
            logger.atInfo().log("Transaction evaluation completed with response: {}", response);
            model.addAttribute("response", response);
            model.addAttribute("success", true);

        }catch(JsonProcessingException e) {
            logger.atError().log("Error occurred while processing JSON: {}", e.getMessage());
            model.addAttribute("error", "JSON inválido: " + e.getMessage());
            model.addAttribute("success", false);

        }
        model.addAttribute("sampleJson", jsonPayload);
        return "fragments/transaction-response :: response-card";
    }
     @GetMapping("/console/reset")
    public String resetAccount(HttpSession session) {
        session.removeAttribute(SESSION_ACCOUNT_KEY);
        return "redirect:/console";
    }
}