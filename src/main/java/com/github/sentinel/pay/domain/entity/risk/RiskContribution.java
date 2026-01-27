package com.github.sentinel.pay.domain.entity.risk;

import lombok.Getter;

/**
 *Risk Points Base on Fraud Rules
 */
@Getter
public enum RiskContribution {
    NONE(0),
    LOW(10),
    MEDIUM(30),
    HIGH(60);
    private final int score;


    RiskContribution(int score) {
        this.score = score;
    }
}
