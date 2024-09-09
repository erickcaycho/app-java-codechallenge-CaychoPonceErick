package com.yape.app.antifraud.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionRequest {
    private String accountExternalIdDebit;
    private String accountExternalIdCredit;
    private Integer transferTypeId;
    private Double value;
}
