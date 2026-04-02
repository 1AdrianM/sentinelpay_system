package com.github.sentinel.pay.application.implementation;

import org.springframework.stereotype.Service;

import com.github.sentinel.pay.application.services.TransactionService;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;
import com.github.sentinel.pay.domain.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public Transaction create(Transaction tx) {
    Transaction saved=  transactionRepository.save(tx);
    if (saved == null) {
        throw new RuntimeException("Failed to save transaction");
    }
    return saved;
    }
    
}
