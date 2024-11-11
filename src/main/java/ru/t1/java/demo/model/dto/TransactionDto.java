package ru.t1.java.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double sumTransaction;
    @JsonProperty("time_transaction")
    private Date timeTransaction;
}
