package com.example.accounting.model;

import java.time.Instant;

public class Account {
    private String id;
    private String name;
    private double balance;
    private Instant createdAt;

    public Account(String id, String name, double balance, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
