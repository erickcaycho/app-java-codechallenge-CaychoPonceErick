package com.yape.app.transaction.service.impl;

import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsumerKafkaServiceImpl {
    private final TransactionService transactionService;

    @KafkaListener(topics = "transactionStatusUpdated", groupId = "group_id")
    public void consume(Transaction transaction){
        transactionService.updateStatusTransaction(
                transaction.getTransactionExternalId(),
                transaction.getStatus()).subscribe();
    }
}