package com.github.sentinel.pay.domain.entity.fraudIncident;

import lombok.Getter;

@Getter
public enum FraudIncidentStatus {
    OPEN(
            "Incidente abierto, pendiente de análisis inicial",
            0,  // No afecta score de riesgo del cliente aún
            true
    ),

    UNDER_REVIEW(
            "Incidente bajo revisión activa por equipo de fraude",
            25, // Incrementa ligeramente el score de riesgo
            true
    ),

    RESOLVED(
            "Incidente resuelto sin determinación de fraude",
            -10, // Reduce el score si fue falsa alarma
            false
    ),

    CONFIRMED_FRAUD(
            "Fraude confirmado - acción tomada",
            100, // Máximo impacto en score de riesgo
            false
    ),

    FALSE_POSITIVE(
            "Determinado como falso positivo - transacción legítima",
            -20, // Reduce score más agresivamente
            false
    );

    private final String description;
    private final int riskScoreImpact;  // Impacto en el customer risk score
    private final boolean isActive;      // Si el caso sigue activo

    FraudIncidentStatus(String description, int riskScoreImpact, boolean isActive) {
        this.description = description;
        this.riskScoreImpact = riskScoreImpact;
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public int getRiskScoreImpact() {
        return riskScoreImpact;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isFinal() {
        return !isActive;
    }

    public boolean requiresAction() {
        return this == OPEN || this == UNDER_REVIEW;
    }
}