package ru.griddodicks.wallet.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionDto implements Serializable {

    private Long transactionId;

    private String itemName;

    private Double itemPrice;

    private Long tagId;

    private String description;

    private Long vaultId;

    private Double sum;

    private String dateStart;

    private String dateEnd;

    private String date;

    private Long accountId;

//    @JsonFormat(pattern = "YYYY-MM-dd")
//    private LocalDateTime date;

    private Long transactionTagId;
}
