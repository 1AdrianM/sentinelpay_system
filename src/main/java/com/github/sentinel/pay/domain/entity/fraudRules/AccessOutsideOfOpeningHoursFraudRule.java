package com.github.sentinel.pay.domain.entity.fraudRules;

import com.github.sentinel.pay.domain.entity.accountRiskProfile.AccountRiskProfile;
import com.github.sentinel.pay.domain.entity.risk.RiskContribution;
import com.github.sentinel.pay.domain.entity.transaction.Transaction;

public record AccessOutsideOfOpeningHoursFraudRule() implements FraudRule {


    @Override
    public RiskContribution evaluateTransaction(Transaction tx, AccountRiskProfile accountRiskProfile) {
       //primero filtramos si son transaction de transferencia externa o internacional, luego validamos la hora de la transaction si esta fuera del rango la anotamos como High risk


/*
        if (tx.getTransactionType().equals(TransactionType.BANK_TRANSFER_EXTERNAL)|| tx.getTransactionType().equals(TransactionType.INTERNATIONAL_TRANSFER && tx.getTimestamp())){
        }*/
        return null;
    }
}
