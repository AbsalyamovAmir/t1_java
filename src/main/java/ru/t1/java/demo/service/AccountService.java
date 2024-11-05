package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.AccountDto;

/**
 * Интерфейс сервиса для работы с сущностью Account
 */
public interface AccountService {

    AccountDto getAccountById(Long id);

    void saveAccount(AccountDto account);

    void generateAccounts(int count);
}
