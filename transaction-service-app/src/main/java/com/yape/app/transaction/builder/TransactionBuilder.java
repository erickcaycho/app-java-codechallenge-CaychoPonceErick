package com.yape.app.transaction.builder;

import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.model.request.TransactionRequest;
import com.yape.app.transaction.model.response.NameValue;
import com.yape.app.transaction.model.response.TransactionResponse;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.yape.app.transaction.util.Util.parseDateToString;

public class TransactionBuilder {
    public static TransactionResponse buildTransactionToResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .transactionExternalId(transaction.getTransactionExternalId())
                .transactionStatus(NameValue.builder().name(transaction.getStatus()).build())
                .transactionType(NameValue.builder().name(transaction.getTransferTypeId()).build())
                .value(transaction.getValue())
                .createdAt(parseDateToString(transaction.getCreatedAt()))
                .build();
    }

    public static Transaction buildTransactionFromRequest(TransactionRequest transactionRequest){
        return Transaction.builder()
                .transactionExternalId(UUID.randomUUID().toString())
                .transferTypeId(transactionRequest.getTransferTypeId())
                .accountExternalIdCredit(transactionRequest.getAccountExternalIdCredit())
                .accountExternalIdDebit(transactionRequest.getAccountExternalIdDebit())
                .status("PENDING")
                .value(transactionRequest.getValue())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
