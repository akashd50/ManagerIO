package com.greymatter.managerio.objects;

import java.time.LocalDateTime;

public class Transaction {
    private int contactID;
    private double amount;
    private LocalDateTime transactionDateTime;
    public Transaction() {
        contactID = -1;
        amount = 0;
        transactionDateTime = LocalDateTime.now();
    }

    public int getContactID() {
        return contactID;
    }

    public Transaction setContactID(int contactID) {
        this.contactID = contactID;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public Transaction setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
        return this;
    }
}
