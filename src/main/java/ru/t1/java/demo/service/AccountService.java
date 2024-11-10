package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.model.Client;

import java.util.List;

/**
 * Интерфейс сервиса для работы с сущностью Account
 */
public interface AccountService {

    AccountDto getAccountById(Long id);

    void saveAccount(AccountDto account);

    void generateAccounts(int count);

    Account findById(Long id);

    List<Account> findAll();
}
