package com.example.accounting;

import com.example.accounting.model.Account;
import com.example.accounting.model.Transaction;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TransactionService implements TransactionUseCase {
    private final Map<String, Transaction> transactions = new ConcurrentHashMap<>();
    private final AccountService accountService; // Assuming AccountService is available for balance updates

    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Transaction createTransaction(String accountId, String type, String category, double amount, String description, LocalDate date) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountId);
        }

        // Update account balance
        if ("income".equals(type)) {
            account.setBalance(account.getBalance() + amount);
        } else if ("expense".equals(type)) {
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new IllegalArgumentException("Invalid transaction type: " + type);
        }

        String id = "txn_" + (transactions.size() + 1);
        Transaction transaction = new Transaction(id, accountId, type, category, amount, description, date, Instant.now());
        transactions.put(id, transaction);
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransactions(String accountId, String category) {
        return transactions.values().stream()
                .filter(txn -> (accountId == null || txn.getAccountId().equals(accountId)))
                .filter(txn -> (category == null || txn.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    @Override
    public Transaction getTransactionById(String transactionId) {
        return transactions.get(transactionId);
    }

    @Override
    public Transaction updateTransaction(String transactionId, String type, String category, double amount, String description, LocalDate date) {
        Transaction existingTransaction = transactions.get(transactionId);
        if (existingTransaction == null) {
            return null; // Or throw an exception
        }

        // Revert old transaction's impact on account balance
        Account account = accountService.getAccountById(existingTransaction.getAccountId());
        if (account == null) {
            throw new IllegalStateException("Associated account not found for transaction: " + transactionId);
        }

        if ("income".equals(existingTransaction.getType())) {
            account.setBalance(account.getBalance() - existingTransaction.getAmount());
        } else if ("expense".equals(existingTransaction.getType())) {
            account.setBalance(account.getBalance() + existingTransaction.getAmount());
        }

        // Apply new transaction's impact on account balance
        if ("income".equals(type)) {
            account.setBalance(account.getBalance() + amount);
        } else if ("expense".equals(type)) {
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new IllegalArgumentException("Invalid transaction type: " + type);
        }

        existingTransaction.setType(type);
        existingTransaction.setCategory(category);
        existingTransaction.setAmount(amount);
        existingTransaction.setDescription(description);
        existingTransaction.setDate(date);
        return existingTransaction;
    }

    @Override
    public void deleteTransaction(String transactionId) {
        Transaction removedTransaction = transactions.remove(transactionId);
        if (removedTransaction != null) {
            Account account = accountService.getAccountById(removedTransaction.getAccountId());
            if (account != null) {
                if ("income".equals(removedTransaction.getType())) {
                    account.setBalance(account.getBalance() - removedTransaction.getAmount());
                } else if ("expense".equals(removedTransaction.getType())) {
                    account.setBalance(account.getBalance() + removedTransaction.getAmount());
                }
            }
        }
    }
}
