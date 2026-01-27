package com.github.sentinel.pay.domain.entity.transaction;

import lombok.Getter;

@Getter
public enum TransactionType {
    // 🏦 Core banking
    CARD_PAYMENT(false,true ),
    CARD_PRESENT_PAYMENT(false,false),
    CARD_NOT_PRESENT_PAYMENT(true,true),

    BANK_TRANSFER_INTERNAL(false,false),
    BANK_TRANSFER_EXTERNAL(true,true),
    INTERNATIONAL_TRANSFER(true,true),

    CASH_WITHDRAWAL_ATM(true,true),
    CASH_DEPOSIT(true,false),

    // 💻 Digital / wallets
    ONLINE_PAYMENT(true,false),
    //WALLET_TOP_UP,
    //WALLET_TRANSFER,

    // 📱 User actions
    BILL_PAYMENT(true,false),
    SUBSCRIPTION_PAYMENT(true,false),

    // 🔄 Reversals & adjustments
    REFUND(true,false),
    CHARGEBACK(true,false),
    REVERSAL(true,false),

    // 🧪 Risky / edge cases
    CRYPTO_PURCHASE(true,true),
    CRYPTO_TRANSFER(true,true),

    // 🧾 Fees / system
    FEE(true,false),
    INTEREST(true,false),
    ADJUSTMENT(true,false);
    private final boolean remote;
    private final boolean highRisk;

    TransactionType(boolean remote, boolean highRisk) {
    this.highRisk=highRisk;
    this.remote=remote;
    }
}
