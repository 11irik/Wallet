package ru.griddodicks.wallet.dto;

import lombok.Data;

@Data
public class UserInviteDto {
    private String email;
    private Long accountId;
}
