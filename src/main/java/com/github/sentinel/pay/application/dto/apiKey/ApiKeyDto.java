package com.github.sentinel.pay.application.dto.apiKey;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public class ApiKeyDto {
    public UUID id;
    public String nameKey;
    public String maskedValue;
    public String value;
    public Instant createdAt;
}
