package com.greymatter.managerio.db.contracts;

import android.provider.BaseColumns;

public class TransactionContract {
    public static class TransactionEntry implements BaseColumns {
        public static final String TABLE_NAME = "transactions";
        public static final String OWNING_USER_ID = "owning_user_id";
        public static final String ASSOCIATED_CONTACT_ID = "contact_id";
        public static final String TRANSACTION_AMOUNT = "transaction_amount";
        public static final String TRANSACTION_DATETIME = "transaction_datetime";
    }
}
