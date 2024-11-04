package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.dto.TransactionDto;

public interface TransactionService {

    TransactionDto getAccountById(Long id);

    void saveAccount(TransactionDto account);
}
