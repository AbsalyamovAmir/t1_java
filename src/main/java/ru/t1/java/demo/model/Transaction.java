package ru.t1.java.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

/**
 * Сущность Transaction
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    /**
     * Сумма транзакции
     */
    @Column(name = "sum_transaction")
    private double sumTransaction;

    /**
     * Время транзакции
     */
    @Column(name = "time_transaction")
    private Date timeTransaction;
}