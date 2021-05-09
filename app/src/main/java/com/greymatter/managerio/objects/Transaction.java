package com.greymatter.managerio.objects;

import java.time.LocalDateTime;

public class Transaction extends AObject {
    private Contact associatedContact;
    private AppUser owningUser;
    private float amount;
    private LocalDateTime transactionDateTime;
    public Transaction() {
        super();
        amount = 0;
        transactionDateTime = LocalDateTime.now();
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

    public Contact getAssociatedContact() {
        return associatedContact;
    }

    public void setAssociatedContact(Contact associatedContact) {
        this.associatedContact = associatedContact;
    }

    public AppUser getOwningUser() {
        return owningUser;
    }

    public void setOwningUser(AppUser owningUser) {
        this.owningUser = owningUser;
    }
}
