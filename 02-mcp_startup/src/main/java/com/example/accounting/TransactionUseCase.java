package com.example.accounting;

import com.example.accounting.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionUseCase {
    Transaction createTransaction(String accountId, String type, String category, double amount, String description, LocalDate date);
    List<Transaction> getAllTransactions(String accountId, String category);
    Transaction getTransactionById(String transactionId);
    Transaction updateTransaction(String transactionId, String type, String category, double amount, String description, LocalDate date);
    void deleteTransaction(String transactionId);
}
