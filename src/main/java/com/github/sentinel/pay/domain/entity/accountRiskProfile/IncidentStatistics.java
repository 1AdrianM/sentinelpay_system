package com.github.sentinel.pay.domain.entity.accountRiskProfile;

import com.github.sentinel.pay.domain.entity.fraudIncident.FraudIncidentStatus;

import java.time.Instant;
import java.util.List;

public record IncidentStatistics(
        List<FraudIncidentStatus> status,
        Instant createdAt
) {


    public int length(){
        if (this.status().isEmpty()) return 0;
        return this.status().size();
    }
}
