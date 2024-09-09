package com.yape.app.transaction.controller;

import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.model.request.TransactionRequest;
import com.yape.app.transaction.model.response.TransactionResponse;
import com.yape.app.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Test
    public void createTransaction() {
        // Given
        TransactionRequest transactionRequest = new TransactionRequest("1","1","1",2.0);
        Transaction transaction = new Transaction();
        when(transactionService.createTransaction(any(TransactionRequest.class))).thenReturn(Mono.just(transaction));

        // When
        Mono<Transaction> responseMono = transactionController.createTransaction(transactionRequest);

        // Then
        StepVerifier.create(responseMono)
                .expectNext(transaction)
                .verifyComplete();
    }
}