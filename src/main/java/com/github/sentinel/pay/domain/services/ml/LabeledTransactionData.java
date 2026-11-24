package com.github.sentinel.pay.domain.services.ml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Labeled transaction data for ML model training.
 * Contains features + ground truth fraud label.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabeledTransactionData {
    
    private TransactionFeatureData featureData;
    private boolean fraud;  // Ground truth label
    private String fraudType; // Type of fraud detected (optional)
}
