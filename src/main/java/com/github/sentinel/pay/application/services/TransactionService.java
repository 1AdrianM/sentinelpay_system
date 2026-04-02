package com.github.sentinel.pay.application.services;

import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public interface TransactionService {
Transaction create(Transaction tx);   
}
