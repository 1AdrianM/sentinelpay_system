package com.github.sentinel.pay.domain.entity.risk;
public enum RiskImpactScale {
    TOTAL(1.0),       // La regla decide por sí sola
    SIGNIFICANT(0.8), // Gran influencia
    MODERATE(0.5),    // Influencia media
    REDUCED(0.2),     // Solo suma si hay otras señales
    MINIMAL(0.1);     // Casi informativa

    private final double weight;
    RiskImpactScale(double weight) { this.weight = weight; }
    public double getWeight() { return this.weight; }
}