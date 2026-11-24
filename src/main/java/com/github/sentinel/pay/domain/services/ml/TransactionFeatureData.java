package com.github.sentinel.pay.domain.services.ml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Transaction feature data for ML model input.
 * Contains all extracted features used for fraud prediction.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFeatureData {
    
    private String transactionId;
    
    // Amount features
    private double amount;
    private double avgTxAmount30Days;
    
    // Time features
    private int hourOfDay;
    private int dayOfWeek;
    private double minutesSinceLastTx;
    
    // Behavioral features
    private int txCountLastHour;
    private double velocityScore;  // Rate of transactions
    
    // Location features
    private double distanceFromHome; // km
    
    // Merchant features
    private double merchantRiskScore; // 0.0 to 1.0
    
    // Account features
    private int accountAgeDays;
}
