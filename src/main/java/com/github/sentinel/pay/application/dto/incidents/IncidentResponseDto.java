package com.github.sentinel.pay.application.dto.incidents;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;
@Builder
public class IncidentResponseDto {
    private UUID incidentId;
    private UUID accountId;
    private String status;
    private int riskScore;
    private Instant openedAt;
    private Instant resolvedAt;
}
