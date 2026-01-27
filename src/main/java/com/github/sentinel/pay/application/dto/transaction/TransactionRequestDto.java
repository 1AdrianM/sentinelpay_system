package com.github.sentinel.pay.application.dto.transaction;

import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.Money;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class TransactionRequestDto implements Serializable {
    private UUID accountId;
    private String transactionType;
    private Money money;//record
    public Location location;
    private String channel;


}
