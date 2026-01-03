package com.github.sentinel.pay.domain.entity.fraudRules;

import lombok.Data;
import lombok.Setter;

public enum RiskContribution {
    NONE(0),
    LOW(10),
    MEDIUM(30),
    HIGH(60);
    private final int score;


    RiskContribution(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
}
