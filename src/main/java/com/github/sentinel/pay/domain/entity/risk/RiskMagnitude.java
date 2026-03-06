package com.github.sentinel.pay.domain.entity.risk;

public enum RiskMagnitude {
    CRITICAL(100),
    HIGH(75),
    MEDIUM(50),
    LOW(25),
    NEGLIGIBLE(0);

    private final int score;
    RiskMagnitude(int score) { this.score = score; }
    public int getScore() { return score; }
}