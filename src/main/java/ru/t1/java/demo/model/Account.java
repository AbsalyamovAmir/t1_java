package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;
import ru.t1.java.demo.enums.AccountType;

/**
 * Сущность Account
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Тип аккаунта
     */
    @Column(name = "account_type")
    private AccountType accountType;

    /**
     * Баланс
     */
    @Column(name = "balance")
    private double balance;
}
