package ru.t1.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.t1.java.demo.aop.DataSourceErrorLogTrack;
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.service.AccountService;

/**
 * Контроллер для работы с сущностью Account
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Создание Account
     * @param accountDto Dto для Account
     */
    @PostMapping
    public void createAccount(@RequestBody AccountDto accountDto) {
        accountService.saveAccount(accountDto);
    }

    /**
     * Генерация записей Account по заданному количеству
     * @param count Количество записей
     */
    @PostMapping("/generate")
    public void generateAccounts(@RequestParam int count) {
        accountService.generateAccounts(count);
    }

    /**
     * Получение Account по id
     * @param id идентификатор Account
     * @return Dto для Account
     */
    @DataSourceErrorLogTrack
    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    /**
     * Метод для тестирования @DataSourceErrorLogTrack
     * @throws RuntimeException Исключение для тестирования
     */
    @DataSourceErrorLogTrack
    @GetMapping("/test-error")
    public void testError() throws Exception {
        throw new Exception("Тестовое исключение для проверки логирования Transaction.");
    }
}
