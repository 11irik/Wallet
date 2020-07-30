package site.kiri11.wallet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import site.kiri11.wallet.domain.TransactionTag;
import site.kiri11.wallet.domain.Vault;
import site.kiri11.wallet.domain.Views;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
