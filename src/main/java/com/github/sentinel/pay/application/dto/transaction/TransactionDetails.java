package com.github.sentinel.pay.application.dto.transaction;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
@Data
 @Builder
public class TransactionDetails {
                                public UUID transactionId ;
                               public int Amount;
                                public  String Currency;
                                public  String Type;
                                public  String Location;
                               public   Instant TimeStamp;
}
