package com.github.sentinel.pay.application.dto.riskProfile;

public record AccountSnapShot(
        String riskLevel,
        int averageAmount,
        String usualLocation,
        int previousIntent
) {
}
