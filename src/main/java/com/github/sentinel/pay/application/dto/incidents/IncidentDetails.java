package com.github.sentinel.pay.application.dto.incidents;

import com.github.sentinel.pay.application.dto.riskProfile.AccountSnapShot;
import com.github.sentinel.pay.application.dto.transaction.TransactionDetails;
import lombok.Builder;

import java.util.UUID;

@Builder
public class IncidentDetails {
    public UUID incidentId;
    public int riskScore;
    public String status;
    public TransactionDetails transactionDetails;
    public AccountSnapShot accountSnapShot;
}
