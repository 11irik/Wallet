package ru.griddodicks.wallet.dto;

import lombok.Data;

@Data
public class ItemDto {
    private Long transactionId;
    private String itemName;
    private Double itemPrice;
    private Long tagId;
}
