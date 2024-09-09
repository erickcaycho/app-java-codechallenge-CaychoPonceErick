package com.yape.app.transaction.service.impl;

import com.yape.app.transaction.builder.TransactionBuilder;
import com.yape.app.transaction.model.entity.Transaction;
import com.yape.app.transaction.model.request.TransactionRequest;
import com.yape.app.transaction.model.response.TransactionResponse;
import com.yape.app.transaction.repository.TransactionRepository;
import com.yape.app.transaction.service.TransactionService;
import com.yape.app.transaction.util.Util;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;
import static com.yape.app.transaction.builder.TransactionBuilder.buildTransactionFromRequest;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProducerKafkaServiceImpl producerServiceKafka;

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Override
    public Mono<Transaction> createTransaction(TransactionRequest transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setTransactionExternalId(UUID.randomUUID().toString());
        transaction.setAccountExternalIdDebit(transactionDto.getAccountExternalIdDebit());
        transaction.setAccountExternalIdCredit(transactionDto.getAccountExternalIdCredit());
        transaction.setTransferTypeId(transactionDto.getTransferTypeId());
        transaction.setValue(transactionDto.getValue());
        transaction.setStatus(Util.PENDING);
        transaction.setCreatedAt(LocalDateTime.now());
        return transactionRepository.save(buildTransactionFromRequest(transactionDto))
                .doOnSuccess(producerServiceKafka::sendCreatedEventTransaction)
                .onErrorResume(error -> {
                    logger.error("Error en createTransaction", error);
                    return Mono.error(error);
                });
    }

    @Override
    public Mono<TransactionResponse> getTransaction(String transactionExternalId) {
        return transactionRepository.findById(transactionExternalId)
                .map(TransactionBuilder::buildTransactionToResponse);
    }

    @Override
    public Mono<Transaction> updateStatusTransaction(String transactionExternalId, String status) {
        return transactionRepository.findById(transactionExternalId)
                .flatMap(transaction -> {
                    transaction.setStatus(status);
                    return transactionRepository.save(transaction);
                });
    }
}