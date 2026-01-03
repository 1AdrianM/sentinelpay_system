package com.github.sentinel.pay.domain.entity.transactionAggregate;

import java.time.Instant;

public class Transaction {
    private Long transactionId;
    private Long accountId;
    private Money money;//record
    private Instant timestamp;
    private Location location; // record
    private Channel   channel;


}
