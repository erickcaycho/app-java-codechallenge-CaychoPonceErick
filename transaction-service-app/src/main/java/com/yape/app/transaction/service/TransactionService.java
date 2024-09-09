package com.yape.app.transaction.service;

import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.model.request.TransactionRequest;
import com.yape.app.transaction.model.response.TransactionResponse;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> createTransaction(TransactionRequest transactionDto);
    Mono<TransactionResponse> getTransaction(String transactionExternalId);
    Mono<Transaction> updateStatusTransaction(String transactionExternalId, String status);
}
