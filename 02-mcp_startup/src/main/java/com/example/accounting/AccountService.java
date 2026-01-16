package com.example.accounting;

import com.example.accounting.model.Account;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountService implements AccountUseCase {
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public Account createAccount(String name, double initialBalance) {
        String id = "acc_" + (accounts.size() + 1);
        Account account = new Account(id, name, initialBalance, Instant.now());
        accounts.put(id, account);
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Account getAccountById(String accountId) {
        return accounts.get(accountId);
    }

    @Override
    public Account updateAccount(String accountId, String name) {
        Account account = accounts.get(accountId);
        if (account != null) {
            account.setName(name);
        }
        return account;
    }

    @Override
    public void deleteAccount(String accountId) {
        accounts.remove(accountId);
    }
}
