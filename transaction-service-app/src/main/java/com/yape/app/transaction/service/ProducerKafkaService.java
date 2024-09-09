package com.yape.app.transaction.service;

import com.yape.app.transaction.model.entity.Transaction;

public interface ProducerKafkaService {
    void sendCreatedEventTransaction(Transaction transaction);
}
