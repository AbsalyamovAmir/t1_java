package ru.t1.java.demo.service;

import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.model.dto.TransactionDto;

/**
 * Интерфейс сервиса для работы с сущностью Transaction
 */
public interface TransactionService {

    TransactionDto getTransactionById(Long id);

    Transaction saveTransaction(TransactionDto transactionDto);

    void generateTransactions(int count);

    Transaction sendTransaction(TransactionDto transactionDto);
}
