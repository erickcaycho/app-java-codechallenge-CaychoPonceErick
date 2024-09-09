package com.yape.app.antifraud.service.impl;

import com.yape.app.antifraud.model.entity.Transaction;
import com.yape.app.antifraud.service.AntifraudService;
import com.yape.app.antifraud.service.ProducerServiceKafka;
import com.yape.app.antifraud.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AntifraudServiceImpl implements AntifraudService {

    private final ProducerServiceKafka kafkaProducerService;

    @Override
    public Mono<Transaction> validateTransaction(Transaction transaction) {
        if (transaction.getValue() > 1000) {
            transaction.setStatus(Util.REJECTED);
        } else {
            transaction.setStatus(Util.APPROVED);
        }
        return Mono.just(transaction)
                .doOnSuccess(kafkaProducerService::sendTransactionStatusEvent);
    }
}