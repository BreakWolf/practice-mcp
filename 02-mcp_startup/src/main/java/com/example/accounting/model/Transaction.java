package com.example.accounting.model;

import java.time.Instant;
import java.time.LocalDate;

public class Transaction {
    private String id;
    private String accountId;
    private String type; // income or expense
    private String category;
    private double amount;
    private String description;
    private LocalDate date;
    private Instant createdAt;

    public Transaction(String id, String accountId, String type, String category, double amount, String description, LocalDate date, Instant createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
