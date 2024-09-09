package com.yape.app.antifraud.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactions")
@Getter
@Setter
public class Transaction {
    @Id
    private String transactionExternalId;
    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private String transferTypeId;
    private Double value;
    private String status;
    private LocalDateTime createdAt;
}
