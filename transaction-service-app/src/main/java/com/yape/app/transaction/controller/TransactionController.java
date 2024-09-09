package com.yape.app.transaction.controller;

import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.model.request.TransactionRequest;
import com.yape.app.transaction.model.response.TransactionResponse;
import com.yape.app.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaction> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.createTransaction(transactionRequest);
    }

    @GetMapping("/{transactionExternalId}")
    public Mono<ResponseEntity<TransactionResponse>> getTransaction(@PathVariable String transactionExternalId) {
        return transactionService.getTransaction(transactionExternalId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}