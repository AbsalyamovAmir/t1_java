package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.TransactionDto;

public interface TransactionService {

    TransactionDto getTransactionById(Long id);

    void saveTransaction(TransactionDto transactionDto);

    void generateTransactions(int count);
}
