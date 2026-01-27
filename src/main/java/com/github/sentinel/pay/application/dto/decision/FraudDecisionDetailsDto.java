package com.github.sentinel.pay.application.dto.decision;

import com.github.sentinel.pay.application.dto.incidents.IncidentDTO;
import com.github.sentinel.pay.application.dto.riskProfile.AccountDTO;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FraudDecisionDetailsDto {
    private UUID decisionId;
    private String status;
    private String riskLevel;
    private String actor;
    private Instant createdAt;
    private Instant updatedAt;
    private String description;

    private List<IncidentDTO> linkedIncidents;
    private List<AccountDTO> relatedAccounts;
    private List<AuditEventDTO> auditTimeline;


}

