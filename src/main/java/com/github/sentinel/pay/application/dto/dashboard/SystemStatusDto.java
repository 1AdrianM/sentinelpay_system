package com.github.sentinel.pay.application.dto.dashboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemStatusDto {
    private String status;
    private String version;
    private long uptimeSeconds;
    private int activeRules;
}
