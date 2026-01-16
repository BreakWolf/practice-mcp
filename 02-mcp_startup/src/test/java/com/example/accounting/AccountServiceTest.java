package com.example.accounting;

import com.example.accounting.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }

    @Test
    void createAccount() {
        Account account = accountService.createAccount("Test Account", 100.0);
        assertNotNull(account);
        assertEquals("Test Account", account.getName());
        assertEquals(100.0, account.getBalance());
        assertNotNull(account.getId());
        assertNotNull(account.getCreatedAt());
    }

    @Test
    void getAllAccounts() {
        accountService.createAccount("Account 1", 100.0);
        accountService.createAccount("Account 2", 200.0);

        List<Account> accounts = accountService.getAllAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    void getAccountById() {
        Account account1 = accountService.createAccount("Account 1", 100.0);
        Account foundAccount = accountService.getAccountById(account1.getId());
        assertNotNull(foundAccount);
        assertEquals(account1.getName(), foundAccount.getName());
    }

    @Test
    void updateAccount() {
        Account account = accountService.createAccount("Old Name", 100.0);
        Account updatedAccount = accountService.updateAccount(account.getId(), "New Name");

        assertNotNull(updatedAccount);
        assertEquals("New Name", updatedAccount.getName());
        assertEquals("New Name", accountService.getAccountById(account.getId()).getName());
    }

    @Test
    void deleteAccount() {
        Account account = accountService.createAccount("Account to Delete", 100.0);
        assertNotNull(accountService.getAccountById(account.getId()));

        accountService.deleteAccount(account.getId());
        assertNull(accountService.getAccountById(account.getId()));
    }
}
