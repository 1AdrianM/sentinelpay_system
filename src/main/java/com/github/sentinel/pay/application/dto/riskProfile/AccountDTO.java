package com.github.sentinel.pay.application.dto.riskProfile;

import lombok.Data;

@Data
public class AccountDTO {
    private Long accountId;
    private String accountName;
    private int riskScore;
}
