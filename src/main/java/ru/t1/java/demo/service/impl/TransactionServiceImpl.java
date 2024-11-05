package ru.t1.java.demo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1.java.demo.dto.TransactionDto;
import ru.t1.java.demo.generator.DataGenerator;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.model.Transaction;
import ru.t1.java.demo.repository.TransactionRepository;
import ru.t1.java.demo.service.TransactionService;
import ru.t1.java.demo.util.TransactionMapper;

import java.util.List;

/**
 * Сервис для работы с сущностью Transaction
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    /**
     * Получение Transaction по id
     * @param id идентификатор записи Transaction
     * @return Dto для Transaction
     */
    @Override
    public TransactionDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        return TransactionMapper.toDto(transaction);
    }

    /**
     * Сохранение Transaction
     * @param transactionDto полученная Dto для Transaction
     */
    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        transactionRepository.save(TransactionMapper.toEntity(transactionDto));
    }

    /**
     * Генерация записей Transaction
     * @param count количество записей
     */
    @Override
    public void generateTransactions(int count) {
        List<Transaction> transactions = DataGenerator.generateTransactions(count);
        transactionRepository.saveAll(transactions);
    }
}
