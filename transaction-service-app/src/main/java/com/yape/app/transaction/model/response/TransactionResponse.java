package com.yape.app.transaction.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransactionResponse {
    private String transactionExternalId;
    private NameValue transactionType;
    private NameValue transactionStatus;
    private Double value;
    private String createdAt;
}