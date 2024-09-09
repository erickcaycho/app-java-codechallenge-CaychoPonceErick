package com.yape.app.antifraud.service.impl;

import com.yape.app.antifraud.model.entity.Transaction;
import com.yape.app.antifraud.service.ProducerServiceKafka;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerServiceKafkaImpl implements ProducerServiceKafka {

    private ReactiveKafkaProducerTemplate<String, Transaction> kafkaTemplate;
    @Override
    public void sendTransactionStatusEvent(Transaction transaction) {
        kafkaTemplate.send("transactionStatusUpdated", transaction).subscribe();
    }
}