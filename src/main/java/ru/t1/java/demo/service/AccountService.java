package ru.t1.java.demo.service;

import ru.t1.java.demo.model.dto.AccountDto;
import ru.t1.java.demo.model.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Интерфейс сервиса для работы с сущностью Account
 */
public interface AccountService {

    AccountDto getAccountById(Long id);

    Account saveAccount(AccountDto account);

    void generateAccounts(int count);

    Account findById(Long id);

    List<Account> findAll();

    Account sendAccount(AccountDto accountDto);

    Account updateAccountSum(Account account, BigDecimal sum);
}
