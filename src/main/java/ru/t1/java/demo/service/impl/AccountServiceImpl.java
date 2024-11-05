package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.generator.DataGenerator;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.repository.AccountRepository;
import ru.t1.java.demo.service.AccountService;
import ru.t1.java.demo.util.AccountMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        return AccountMapper.toDto(account);
    }

    @Override
    public void saveAccount(AccountDto account) {
        accountRepository.save(AccountMapper.toEntity(account));
    }

    @Override
    public void createAccounts(int count) {
        List<Account> accounts = DataGenerator.generateAccounts(count);
        accountRepository.saveAll(accounts);
    }
}
