package com.example.accounting;

import com.example.accounting.model.Account;

import java.util.List;

public interface AccountUseCase {
    Account createAccount(String name, double initialBalance);
    List<Account> getAllAccounts();
    Account getAccountById(String accountId);
    Account updateAccount(String accountId, String name);
    void deleteAccount(String accountId);
}
