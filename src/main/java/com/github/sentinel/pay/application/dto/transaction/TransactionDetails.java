package com.github.sentinel.pay.application.dto.transaction;

import java.time.Instant;

public record TransactionDetails (
                                int Amount,
                                  String Currency,
                                  String Type,
                                  String Location,
                                  Instant TimeStamp){
}
