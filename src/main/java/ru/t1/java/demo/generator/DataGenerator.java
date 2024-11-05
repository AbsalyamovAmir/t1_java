package ru.t1.java.demo.generator;

import ru.t1.java.demo.enums.AccountType;
import ru.t1.java.demo.model.Account;
import ru.t1.java.demo.model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DataGenerator {

    private static final Random random = new Random();

    /**
     * Генератор для Account
     * @param count Необходимое количество записей
     * @return      Список объектов Account
     */
    public static List<Account> generateAccounts(int count) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Account account = new Account();
            account.setAccountType(random.nextBoolean() ? AccountType.DEBIT : AccountType.CREDIT);
            account.setBalance(random.nextDouble() * (10000 - 100) + 100);
            accounts.add(account);
        }
        return accounts;
    }

    /**
     * Генератор для Transaction
     * @param count Необходимое количество записей
     * @return      Список объектов Transaction
     */
    public static List<Transaction> generateTransactions(int count) {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Transaction transaction = new Transaction();
            transaction.setSumTransaction(random.nextDouble() * (1000 - 1) + 1);
            long currentTimeMillis = System.currentTimeMillis();
            long pastTimeMillis = currentTimeMillis - TimeUnit.DAYS.toMillis(random.nextInt(30));
            Date transactionTime = new Date(pastTimeMillis);
            transaction.setTimeTransaction(transactionTime);
            transactions.add(transaction);
        }
        return transactions;
    }
}
