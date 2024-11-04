package ru.t1.java.demo.util;

import jakarta.persistence.Column;
import org.springframework.stereotype.Component;
import ru.t1.java.demo.dto.AccountDto;
import ru.t1.java.demo.dto.ClientDto;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.model.Client;

@Component
public class AccountMapper {

    public static Account toEntity(AccountDto accountDto) {
        return Account.builder()
                .accountType(accountDto.getAccountType())
                .balance(accountDto.getBalance())
                .build();
    }

    public static AccountDto toDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .accountType(entity.getAccountType())
                .balance(entity.getBalance())
                .build();
    }
}
