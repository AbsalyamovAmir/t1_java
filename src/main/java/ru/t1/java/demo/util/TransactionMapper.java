package ru.t1.java.demo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.dto.TransactionDto;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.service.AccountService;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    private final AccountService accountService;

    public Transaction toEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .account(accountService.findById(transactionDto.getAccountId()))
                .sumTransaction(transactionDto.getSumTransaction())
                .timeTransaction(transactionDto.getTimeTransaction())
                .transactionStatus(transactionDto.getTransactionStatus())
                .transactionId(transactionDto.getTransactionId())
                .timestamp(transactionDto.getTimestamp())
                .build();
    }

    public TransactionDto toDto(Transaction entity) {
        return TransactionDto.builder()
                .id(entity.getId())
                .sumTransaction(entity.getSumTransaction())
                .timeTransaction(entity.getTimeTransaction())
                .transactionStatus(entity.getTransactionStatus())
                .transactionId(entity.getTransactionId())
                .timestamp(entity.getTimestamp())
                .build();
    }
}
