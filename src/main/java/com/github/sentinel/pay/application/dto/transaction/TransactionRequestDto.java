package com.github.sentinel.pay.application.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.sentinel.pay.domain.entity.shared.Location;
import com.github.sentinel.pay.domain.entity.transaction.Money;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class TransactionRequestDto implements Serializable {
    @JsonProperty("accountId")
    public UUID accountId;
    @JsonProperty("transactionType")
    public String transactionType;
    @JsonProperty("amount")
    public String amount;
    @JsonProperty("currency")
    public String currency;
    @JsonProperty("city")
    public String city;
    @JsonProperty("country")
    public String country;
     @JsonProperty("channel")
    private String channel;


}
