package com.github.sentinel.pay.application.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Response DTO for ML fraud prediction API.
 * Contains fraud probability, risk level, and feature analysis.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MLFraudPredictionResponse {
    
    private String transactionId;
    
    // Fraud probability (0.0 to 1.0)
    private double fraudProbability;
    
    // Fraud probability as percentage (0-100)
    private long fraudProbabilityPercent;
    
    // Risk level classification
    private String riskLevel;  // LOW, MEDIUM, HIGH
    
    // Extracted feature vector (10 dimensions)
    private double[] features;
    
    // Feature importance explanation
    private String featureImportance;
    
    // Model metadata
    private String modelVersion;
    
    // Prediction timestamp
    private Instant timestamp;
}
