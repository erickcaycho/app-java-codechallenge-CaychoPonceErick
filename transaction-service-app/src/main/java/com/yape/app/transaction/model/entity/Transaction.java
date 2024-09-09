package com.yape.app.transaction.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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