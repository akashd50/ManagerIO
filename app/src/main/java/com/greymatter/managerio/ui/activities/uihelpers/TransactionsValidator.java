package com.greymatter.managerio.ui.activities.uihelpers;

import com.greymatter.managerio.objects.Transaction;

public class TransactionsValidator {
    public static boolean validateTransaction(Transaction transaction) throws Exception {
        if (transaction.getAmount() < 0.0) {
            throw new Exception("Invalid Amount");
        }
        return true;
    }
}
