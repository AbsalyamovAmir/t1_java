package ru.t1.java.demo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.model.dto.AccountDto;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.service.ClientService;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final ClientService clientService;

    public Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .client(clientService.findById(accountDto.getClientId()))
                .accountType(accountDto.getAccountType())
                .balance(accountDto.getBalance())
                .build();
    }

    public AccountDto toDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .accountType(entity.getAccountType())
                .balance(entity.getBalance())
                .build();
    }
}
