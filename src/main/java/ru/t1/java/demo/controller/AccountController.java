package ru.t1.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.t1.java.demo.aop.DataSourceErrorLogTrack;
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@DataSourceErrorLogTrack
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void createAccount(@RequestBody AccountDto accountDto) {
        accountService.saveAccount(accountDto);
    }

    @PostMapping
    public void generateAccounts(@RequestBody int count) {
        accountService.createAccounts(count);
    }

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }
}
