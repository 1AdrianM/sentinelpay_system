package com.github.sentinel.pay.application.dto.decision;

import com.github.sentinel.pay.application.dto.incidents.IncidentDTO;
 import com.github.sentinel.pay.application.dto.transaction.TransactionDetails;

import lombok.Builder;
 import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class FraudDecisionDetailsDto {
    private UUID decisionId;
    private String decisionType;
    private String actor;
    private Instant issuedAt;
    private Instant modifiedAt;
    private String description;

    private List<IncidentDTO> linkedIncidents;
    private TransactionDetails transactionDetails;
    private List<AuditEventDto> auditTimeline;


}

