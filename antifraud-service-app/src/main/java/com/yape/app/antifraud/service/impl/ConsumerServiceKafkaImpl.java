package com.yape.app.antifraud.service.impl;

import com.yape.app.antifraud.model.entity.Transaction;
import com.yape.app.antifraud.service.AntifraudService;
import com.yape.app.antifraud.service.ConsumerServiceKafka;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsumerServiceKafkaImpl implements ConsumerServiceKafka {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerServiceKafkaImpl.class);
    private final AntifraudService antiFraudService;

        @Override
        @KafkaListener(topics = "transactionCreated", groupId = "group_id")
        public void consume(@Payload Transaction transaction) {
            logger.debug("[Consume Started]");
            antiFraudService.validateTransaction(transaction).subscribe();
            logger.debug("[Transaction Validated]");
        }
}