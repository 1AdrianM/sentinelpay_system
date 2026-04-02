package com.github.sentinel.pay.application.dto.incidents;

import java.util.UUID;

import lombok.Builder;


@Builder

public class OpenIncidentRequestDto {
    public UUID transactionId;
    public UUID decisionId;
    public int riskScore;
}
