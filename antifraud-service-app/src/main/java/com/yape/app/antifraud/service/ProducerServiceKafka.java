package com.yape.app.antifraud.service;

import com.yape.app.antifraud.model.entity.Transaction;

public interface ProducerServiceKafka {
    void sendTransactionStatusEvent(Transaction transaction);
}
