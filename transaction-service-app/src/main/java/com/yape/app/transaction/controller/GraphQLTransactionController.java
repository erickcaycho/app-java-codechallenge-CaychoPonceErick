package com.yape.app.transaction.controller;

import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.model.request.TransactionRequest;
import com.yape.app.transaction.model.response.TransactionResponse;
import com.yape.app.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@AllArgsConstructor
public class GraphQLTransactionController {

    private TransactionService transactionService;

    @QueryMapping
    public Mono<TransactionResponse> getTransaction(@Argument String transactionExternalId) {
        return transactionService.getTransaction(transactionExternalId);
    }

    @MutationMapping
    public Mono<Transaction> createTransaction(@Argument String accountExternalIdDebit,
                                               @Argument String accountExternalIdCredit,
                                               @Argument String transferTypeId,
                                               @Argument Double value) {

        TransactionRequest transactionRequest =
                TransactionRequest.builder()
                        .accountExternalIdCredit(accountExternalIdCredit)
                        .accountExternalIdDebit(accountExternalIdDebit)
                        .transferTypeId(transferTypeId)
                        .value(value)
                        .build();

        return transactionService.createTransaction(transactionRequest);
    }
}