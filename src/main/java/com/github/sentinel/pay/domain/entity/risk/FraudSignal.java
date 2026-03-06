package com.github.sentinel.pay.domain.entity.risk;
import lombok.Builder;

/**
 * Representa una señal individual de riesgo detectada por el motor de reglas.
 */
public record FraudSignal(
        int score,
        double weight,
        String ruleTriggered,
        String description
) {
    /**
     * Factory method estandarizado para crear señales consistentes.
     */
    public static FraudSignal of(
            RiskMagnitude magnitude,
            RiskImpactScale impact,
            String ruleName,
            String detail
    ) {
        return new FraudSignal(
                magnitude.getScore(),
                impact.getWeight(),
                ruleName,
                detail
        );
    }

    /**
     * Calcula el valor real que aporta esta señal al total.
     */
    public double getWeightedContribution() {
        return score * weight;
    }
}