package com.github.sentinel.pay.application.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
 import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class TransactionRequestDto implements Serializable {
    @NotEmpty
    @NotBlank
    @JsonProperty("accountId")
    public UUID accountId;

    @NotEmpty
    @NotBlank
    @JsonProperty("transactionType")
    public String transactionType;
    
    @NotEmpty
    @NotBlank
    @JsonProperty("amount")
    public String amount;
    
    @NotEmpty
    @NotBlank
    @JsonProperty("currency")
    public String currency;
    
    @NotEmpty
    @NotBlank
    @JsonProperty("city")
    public String city;
    
    @NotEmpty
    @NotBlank
    @JsonProperty("country")
    public String country;
    
    @NotEmpty
    @JsonProperty("channel")
     @NotBlank
    private String channel;


}
