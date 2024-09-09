package com.yape.app.transaction.service.impl;

import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.service.ProducerKafkaService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerKafkaServiceImpl implements ProducerKafkaService {
    private final ReactiveKafkaProducerTemplate<String,Transaction> kafkaProducerTemplate;

    @Override
    public void sendCreatedEventTransaction(Transaction transaction) {
        kafkaProducerTemplate.send("transactionCreated", transaction).subscribe();
    }
}