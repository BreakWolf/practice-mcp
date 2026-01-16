package com.example.accounting;

import com.example.accounting.model.Account;
import com.example.accounting.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
        transactionService = new TransactionService(accountService);
    }

    @Test
    void createTransaction_income() {
        Account account = accountService.createAccount("Salary Account", 0.0);
        Transaction transaction = transactionService.createTransaction(account.getId(), "income", "Salary", 5000.0, "Monthly salary", LocalDate.of(2023, 1, 15));

        assertNotNull(transaction);
        assertEquals(account.getId(), transaction.getAccountId());
        assertEquals("income", transaction.getType());
        assertEquals(5000.0, transaction.getAmount());
        assertEquals(5000.0, accountService.getAccountById(account.getId()).getBalance());
    }

    @Test
    void createTransaction_expense() {
        Account account = accountService.createAccount("Cash Account", 1000.0);
        Transaction transaction = transactionService.createTransaction(account.getId(), "expense", "Food", 150.0, "Lunch", LocalDate.of(2023, 1, 15));

        assertNotNull(transaction);
        assertEquals(account.getId(), transaction.getAccountId());
        assertEquals("expense", transaction.getType());
        assertEquals(150.0, transaction.getAmount());
        assertEquals(850.0, accountService.getAccountById(account.getId()).getBalance());
    }

    @Test
    void getAllTransactions() {
        Account account = accountService.createAccount("Wallet", 1000.0);
        transactionService.createTransaction(account.getId(), "expense", "Food", 50.0, "Coffee", LocalDate.now());
        transactionService.createTransaction(account.getId(), "income", "Gift", 200.0, "Birthday gift", LocalDate.now());

        List<Transaction> transactions = transactionService.getAllTransactions(account.getId(), null);
        assertEquals(2, transactions.size());

        List<Transaction> foodTransactions = transactionService.getAllTransactions(account.getId(), "Food");
        assertEquals(1, foodTransactions.size());
    }

    @Test
    void getTransactionById() {
        Account account = accountService.createAccount("Bank", 1000.0);
        Transaction transaction = transactionService.createTransaction(account.getId(), "expense", "Rent", 500.0, "Monthly rent", LocalDate.now());

        Transaction foundTransaction = transactionService.getTransactionById(transaction.getId());
        assertNotNull(foundTransaction);
        assertEquals(transaction.getDescription(), foundTransaction.getDescription());
    }

    @Test
    void updateTransaction() {
        Account account = accountService.createAccount("Checking", 1000.0);
        Transaction transaction = transactionService.createTransaction(account.getId(), "expense", "Food", 100.0, "Dinner", LocalDate.now());

        Transaction updatedTransaction = transactionService.updateTransaction(transaction.getId(), "expense", "Groceries", 200.0, "Weekly groceries", LocalDate.now());

        assertNotNull(updatedTransaction);
        assertEquals("Groceries", updatedTransaction.getCategory());
        assertEquals(200.0, updatedTransaction.getAmount());
        assertEquals(800.0, accountService.getAccountById(account.getId()).getBalance()); // 1000 - 100 + 100 - 200 = 800
    }

    @Test
    void deleteTransaction() {
        Account account = accountService.createAccount("Savings", 500.0);
        Transaction transaction = transactionService.createTransaction(account.getId(), "income", "Interest", 10.0, "Monthly interest", LocalDate.now());

        assertNotNull(transactionService.getTransactionById(transaction.getId()));
        assertEquals(510.0, accountService.getAccountById(account.getId()).getBalance());

        transactionService.deleteTransaction(transaction.getId());
        assertNull(transactionService.getTransactionById(transaction.getId()));
        assertEquals(500.0, accountService.getAccountById(account.getId()).getBalance());
    }

    @Test
    void createTransaction_accountNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.createTransaction("non_existent_account", "income", "Salary", 100.0, "", LocalDate.now());
        });
    }

    @Test
    void updateTransaction_invalidType() {
        Account account = accountService.createAccount("Test", 100.0);
        Transaction transaction = transactionService.createTransaction(account.getId(), "income", "", 100.0, "", LocalDate.now());
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.updateTransaction(transaction.getId(), "invalid", "", 50.0, "", LocalDate.now());
        });
    }

    @Test
    void deleteTransaction_nullAccount() {
        AccountService malformedAccountService = new AccountService() {
            @Override
            public Account getAccountById(String accountId) {
                return null; // Simulate account not found after transaction creation
            }
        };
        TransactionService malformedTransactionService = new TransactionService(malformedAccountService);
        // Manually create a transaction that would normally be linked to a real account
        Transaction transaction = new Transaction("txn_1", "some_id", "income", "", 100.0, "", LocalDate.now(), Instant.now());
        malformedTransactionService.transactions.put(transaction.getId(), transaction);

        // Deleting this transaction should not throw an error, but the balance won't be reverted
        assertDoesNotThrow(() -> malformedTransactionService.deleteTransaction(transaction.getId()));
        assertNull(malformedTransactionService.getTransactionById(transaction.getId()));
    }
}
