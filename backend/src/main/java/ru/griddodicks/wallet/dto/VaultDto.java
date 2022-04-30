package ru.griddodicks.wallet.dto;

import lombok.Data;

@Data
public class VaultDto {
    private String name;
    private Double sum;
    private Long accountId;
}
