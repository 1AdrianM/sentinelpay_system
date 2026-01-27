package com.github.sentinel.pay.application.dto.decision;

import java.time.Instant;

public class AuditEventDTO {
    private String action;
    private String description;
    private String actor;
    private Instant timestamp;
}
