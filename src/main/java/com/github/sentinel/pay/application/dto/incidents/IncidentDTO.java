package com.github.sentinel.pay.application.dto.incidents;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class IncidentDTO {
    private UUID incidentId;
    private String status;
    private Instant timestamp;
}
