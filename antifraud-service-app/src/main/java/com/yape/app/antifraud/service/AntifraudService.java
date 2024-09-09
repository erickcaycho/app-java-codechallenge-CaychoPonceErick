package com.yape.app.antifraud.service;

import com.yape.app.antifraud.model.entity.Transaction;
import reactor.core.publisher.Mono;

public interface AntifraudService {
    Mono<Transaction> validateTransaction(Transaction transaction);
}
