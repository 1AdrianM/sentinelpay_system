package com.github.sentinel.pay.application.exceptions;

public class TransactionAmountBelowZeroException extends Exception{
    public TransactionAmountBelowZeroException(String message){
     super(message);

    }
}
