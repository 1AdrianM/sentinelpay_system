package com.github.sentinel.pay.domain.entity.risk;

import java.util.Map;

public class RiskWeightPolicy {

    private static final Map<RiskType, Double> WEIGHTS = Map.of(
            RiskType.VELOCITY, 0.35,
            RiskType.LOCATION, 0.25,
            RiskType.AMOUNT, 0.20,
            RiskType.CURRENCY, 0.20
    );

    public double weight(RiskSignal signal) {
        return signal.severity() * WEIGHTS.get(signal.type());
    }
}
