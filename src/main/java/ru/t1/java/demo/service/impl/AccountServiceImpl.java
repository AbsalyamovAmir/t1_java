package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.exception.AccountException;
import ru.t1.java.demo.exception.ClientException;
import ru.t1.java.demo.generator.DataGenerator;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.model.Client;
import ru.t1.java.demo.repository.AccountRepository;
import ru.t1.java.demo.service.AccountService;
import ru.t1.java.demo.service.ClientService;
import ru.t1.java.demo.util.AccountMapper;

import java.util.List;

/**
 * Сервис для работы с сущностью Account
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final AccountMapper accountMapper;

    /**
     * Получение Account по id
     * @param id идентификатор записи Account
     * @return Dto для Account
     */
    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("Account with ID " + id + " not found"));
        return accountMapper.toDto(account);
    }

    /**
     * Сохранение Account
     * @param accountDto полученная Dto для Account
     */
    @Override
    public void saveAccount(AccountDto accountDto) {
        accountRepository.save(accountMapper.toEntity(accountDto));
    }

    /**
     * Генерация записей Account
     * @param count количество записей
     */
    @Override
    public void generateAccounts(int count) {
        List<Client> clients = clientService.findAll();
        if (clients.isEmpty()) {
            throw new ClientException("No clients found");
        }
        List<Account> accounts = DataGenerator.generateAccounts(count, clients);
        accountRepository.saveAll(accounts);
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ClientException("Account with ID " + id + " not found"));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
