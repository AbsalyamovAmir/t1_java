package ru.t1.java.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.t1.java.demo.model.enums.TransactionStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO for {@link ru.t1.java.demo.model.Transaction}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {
    private Long id;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("sum_transaction")
    private BigDecimal sumTransaction;
    @JsonProperty("time_transaction")
    private Date timeTransaction;
    @JsonProperty("transactionStatus")
    private TransactionStatus transactionStatus;
    @JsonProperty("transactionId")
    private long transactionId;
    @JsonProperty("timestamp")
    private Date timestamp;
}
