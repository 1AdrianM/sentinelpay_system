package com.github.sentinel.pay.domain.entity.risk;

public record RiskSignal(
       double severity,
       RiskType  type
) {
}
