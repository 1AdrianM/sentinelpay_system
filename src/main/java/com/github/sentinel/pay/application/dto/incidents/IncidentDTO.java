package com.github.sentinel.pay.application.dto.incidents;

import java.time.Instant;

public class IncidentDTO {
    private Long incidentId;
    private String status;
    private String severity;
    private Instant timestamp;
}
