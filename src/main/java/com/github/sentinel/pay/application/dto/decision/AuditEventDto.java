package com.github.sentinel.pay.application.dto.decision;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class AuditEventDto {
    public String action;
    public String description;
    public String actor;
    public Instant timestamp;
}
