package ru.t1.java.demo.service;

import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.model.Account;

public interface AccountService {

    AccountDto getAccountById(Long id);

    void saveAccount(AccountDto account);
}
