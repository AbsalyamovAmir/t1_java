package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.TransactionDto;

/**
 * Интерфейс сервиса для работы с сущностью Transaction
 */
public interface TransactionService {

    TransactionDto getTransactionById(Long id);

    void saveTransaction(TransactionDto transactionDto);

    void generateTransactions(int count);
}
