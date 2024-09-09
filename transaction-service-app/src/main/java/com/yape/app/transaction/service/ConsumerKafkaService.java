package com.yape.app.transaction.service;

import com.yape.app.transaction.model.entity.Transaction;

public interface ConsumerKafkaService {
    void Consume(Transaction transaction);
}
