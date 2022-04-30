package ru.griddodicks.wallet.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long categoryId;
    private Long tagId;
    private String name;
    private Long accountId;
}
