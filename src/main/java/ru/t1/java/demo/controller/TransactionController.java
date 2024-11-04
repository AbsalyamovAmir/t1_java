package ru.t1.java.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.t1.java.demo.dto.TransactionDto;
import ru.t1.java.demo.service.TransactionService;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public void createAccount(@RequestBody TransactionDto transactionDto) {
        transactionService.saveAccount(transactionDto);
    }

    @GetMapping("/{id}")
    public TransactionDto getAccount(@PathVariable Long id) {
        return transactionService.getAccountById(id);
    }
}
