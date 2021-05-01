package com.greymatter.managerio.objects;

import java.time.LocalDateTime;

public class Transaction extends AObject {
    private int owningUserId, contactID;
    private float amount;
    private LocalDateTime transactionDateTime;
    public Transaction() {
        super();
        contactID = -1;
        amount = 0;
        transactionDateTime = LocalDateTime.now();
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = LocalDateTime.parse(transactionDateTime);
    }

    public int getOwningUserId() {
        return owningUserId;
    }

    public void setOwningUserId(int owningUserId) {
        this.owningUserId = owningUserId;
    }
}
