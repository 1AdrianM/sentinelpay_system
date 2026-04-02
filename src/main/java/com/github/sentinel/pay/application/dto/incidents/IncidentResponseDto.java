package com.github.sentinel.pay.application.dto.incidents;

import lombok.Builder;
 import lombok.Getter;

import java.time.Instant;
import java.util.UUID;
@Builder
@Getter
public class IncidentResponseDto {
    public UUID incidentId;
    public UUID transactionId;
    public UUID accountId;
    public String status;
    public int riskScore;
    public Instant openedAt;
    public Instant resolvedAt;
}
