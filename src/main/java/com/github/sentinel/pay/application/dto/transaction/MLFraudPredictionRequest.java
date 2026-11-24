package com.github.sentinel.pay.application.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for ML fraud prediction API.
 * Contains all feature data needed for ML-based fraud scoring.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MLFraudPredictionRequest {
    
    private String transactionId;
    
    // Amount features
    private double amount;
    private double avgTxAmount30Days;
    
    // Time features
    private int hourOfDay;        // 0-23
    private int dayOfWeek;        // 0-6 (Monday=0)
    private double minutesSinceLastTx;
    
    // Behavioral features
    private int txCountLastHour;
    private double velocityScore;  // 0.0-1.0
    
    // Location feature
    private double distanceFromHome;  // kilometers
    
    // Merchant feature
    private double merchantRiskScore;  // 0.0-1.0
    
    // Account feature
    private int accountAgeDays;
}
