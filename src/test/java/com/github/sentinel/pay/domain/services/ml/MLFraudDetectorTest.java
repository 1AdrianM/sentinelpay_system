package com.github.sentinel.pay.domain.services.ml;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive tests for ML-based fraud detection.
 * 
 * Tests cover:
 * - Feature extraction
 * - Risk probability calculation
 * - Edge cases (high amounts, late night, velocity, distance)
 * - Feature importance weights
 */
class MLFraudDetectorTest {

    private MLFraudDetector detector;

    @BeforeEach
    void setUp() {
        detector = new MLFraudDetector();
    }

    @Test
    @DisplayName("Should extract 10 features from transaction data")
    void shouldExtractTenFeatures() {
        // Given: Normal transaction during business hours
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-123")
            .amount(100.0)
            .avgTxAmount30Days(100.0)
            .hourOfDay(14) // 2 PM
            .dayOfWeek(2)  // Wednesday
            .minutesSinceLastTx(60.0)
            .txCountLastHour(1)
            .distanceFromHome(5.0) // 5km
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);

        // Then
        assertEquals(10, features.length, "Should extract exactly 10 features");
        assertTrue(features[0] >= 0.0 && features[0] <= 1.0, "Amount should be normalized 0-1");
        assertEquals(14, features[1], "Hour should be preserved");
        assertEquals(2, features[2], "Day of week should be preserved");
    }

    @Test
    @DisplayName("Should return low fraud probability for normal transaction")
    void shouldReturnLowProbabilityForNormalTransaction() {
        // Given: Normal business transaction
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-normal")
            .amount(50.0)
            .avgTxAmount30Days(50.0)
            .hourOfDay(10) // 10 AM - business hours
            .dayOfWeek(2)  // Wednesday
            .minutesSinceLastTx(120.0) // 2 hours
            .txCountLastHour(1)
            .distanceFromHome(2.0) // Local
            .merchantRiskScore(0.1) // Low risk merchant
            .accountAgeDays(730) // 2 year old account
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertTrue(probability < 0.3, "Normal transaction should have low fraud probability (<30%)");
        System.out.printf("Normal transaction fraud probability: %.2f%n", probability);
    }

    @Test
    @DisplayName("Should return high fraud probability for suspicious transaction")
    void shouldReturnHighProbabilityForSuspiciousTransaction() {
        // Given: Highly suspicious transaction
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-suspicious")
            .amount(10000.0) // Very high amount
            .avgTxAmount30Days(100.0) // Much higher than usual
            .hourOfDay(2) // 2 AM - unusual time
            .dayOfWeek(6)  // Sunday
            .minutesSinceLastTx(1.0) // Only 1 minute since last
            .txCountLastHour(8) // Many transactions
            .distanceFromHome(600.0) // International
            .merchantRiskScore(0.8) // High risk merchant
            .accountAgeDays(3) // Brand new account
            .velocityScore(0.9) // Very high velocity
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertTrue(probability > 0.7, "Suspicious transaction should have high fraud probability (>70%)");
        System.out.printf("Suspicious transaction fraud probability: %.2f%n", probability);
    }

    @Test
    @DisplayName("Should detect high amount risk")
    void shouldDetectHighAmountRisk() {
        // Given: High amount transaction
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-high-amount")
            .amount(5000.0) // High amount
            .avgTxAmount30Days(100.0) // Normal average
            .hourOfDay(14)
            .dayOfWeek(2)
            .minutesSinceLastTx(120.0)
            .txCountLastHour(1)
            .distanceFromHome(5.0)
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertTrue(probability > 0.15, "High amount should increase fraud probability");
    }

    @Test
    @DisplayName("Should detect late night time risk")
    void shouldDetectLateNightRisk() {
        // Given: Late night transaction
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-late-night")
            .amount(100.0)
            .avgTxAmount30Days(100.0)
            .hourOfDay(3) // 3 AM
            .dayOfWeek(2)
            .minutesSinceLastTx(120.0)
            .txCountLastHour(1)
            .distanceFromHome(5.0)
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertTrue(probability > 0.1, "Late night transactions should have elevated risk");
    }

    @Test
    @DisplayName("Should detect high velocity risk")
    void shouldDetectHighVelocityRisk() {
        // Given: Rapid transactions
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-velocity")
            .amount(100.0)
            .avgTxAmount30Days(100.0)
            .hourOfDay(14)
            .dayOfWeek(2)
            .minutesSinceLastTx(1.0) // 1 minute ago
            .txCountLastHour(6) // 6 transactions in last hour
            .distanceFromHome(5.0)
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.8)
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertTrue(probability > 0.2, "High velocity should significantly increase fraud probability");
    }

    @Test
    @DisplayName("Should detect geographic risk")
    void shouldDetectGeographicRisk() {
        // Given: Transaction from far location
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-far-location")
            .amount(100.0)
            .avgTxAmount30Days(100.0)
            .hourOfDay(14)
            .dayOfWeek(2)
            .minutesSinceLastTx(120.0)
            .txCountLastHour(1)
            .distanceFromHome(1000.0) // 1000km away
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertTrue(probability > 0.1, "Far from home location should increase risk");
    }

    @Test
    @DisplayName("Should account for new account risk")
    void shouldDetectNewAccountRisk() {
        // Given: Transaction from brand new account
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-new-account")
            .amount(100.0)
            .avgTxAmount30Days(100.0)
            .hourOfDay(14)
            .dayOfWeek(2)
            .minutesSinceLastTx(120.0)
            .txCountLastHour(1)
            .distanceFromHome(5.0)
            .merchantRiskScore(0.1)
            .accountAgeDays(2) // 2 days old
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertTrue(probability > 0.05, "New account should have slightly elevated risk");
    }

    @Test
    @DisplayName("Should handle edge case: zero amount")
    void shouldHandleZeroAmount() {
        // Given: Zero amount transaction (testing transaction)
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-zero")
            .amount(0.0)
            .avgTxAmount30Days(100.0)
            .hourOfDay(14)
            .dayOfWeek(2)
            .minutesSinceLastTx(120.0)
            .txCountLastHour(1)
            .distanceFromHome(5.0)
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);
        double probability = detector.calculateFraudProbability(features);

        // Then
        assertNotNull(features, "Should handle zero amount without error");
        assertTrue(probability >= 0.0 && probability <= 1.0, "Probability should be in valid range");
    }

    @Test
    @DisplayName("Feature importance should be documented")
    void shouldProvideFeatureImportance() {
        // When
        String importance = detector.getFeatureImportance();

        // Then
        assertNotNull(importance, "Feature importance should be available");
        assertTrue(importance.contains("30%"), "Should show amount weight");
        assertTrue(importance.contains("20%"), "Should show time and velocity weights");
        assertTrue(importance.contains("15%"), "Should show geo and context weights");
    }

    @Test
    @DisplayName("Should normalize large amounts correctly")
    void shouldNormalizeLargeAmounts() {
        // Given: Very large transaction
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-huge")
            .amount(1000000.0) // $1 million
            .avgTxAmount30Days(100.0)
            .hourOfDay(14)
            .dayOfWeek(2)
            .minutesSinceLastTx(120.0)
            .txCountLastHour(1)
            .distanceFromHome(5.0)
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.1)
            .build();

        // When
        double[] features = detector.extractFeatures(data);

        // Then
        assertTrue(features[0] <= 1.0, "Even huge amounts should normalize to <=1.0");
        assertTrue(features[0] > 0.9, "Million dollar transaction should be near max normalized value");
    }

    @Test
    @DisplayName("Should handle concurrent feature extraction")
    void shouldHandleConcurrentFeatureExtraction() throws InterruptedException {
        // Given: Multiple threads extracting features
        TransactionFeatureData data = TransactionFeatureData.builder()
            .transactionId("tx-concurrent")
            .amount(100.0)
            .avgTxAmount30Days(100.0)
            .hourOfDay(14)
            .dayOfWeek(2)
            .minutesSinceLastTx(120.0)
            .txCountLastHour(1)
            .distanceFromHome(5.0)
            .merchantRiskScore(0.1)
            .accountAgeDays(365)
            .velocityScore(0.1)
            .build();

        // When: Multiple threads use the detector
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                double[] features = detector.extractFeatures(data);
                double probability = detector.calculateFraudProbability(features);
                assertNotNull(features);
                assertTrue(probability >= 0.0 && probability <= 1.0);
            });
            threads[i].start();
        }

        // Then: All threads complete successfully
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
