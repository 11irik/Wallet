package site.kiri11.wallet.dto;

import lombok.Data;
import site.kiri11.wallet.domain.Vault;

@Data
public class ItemDto {
    private Long transactionId;
    private String itemName;
    private Double itemPrice;
    private Long tagId;
}
