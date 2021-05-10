package com.greymatter.managerio.db.contracts;

import android.provider.BaseColumns;

public class IDSContract {
    public static class IDSEntry implements BaseColumns {
        public static final String TABLE_NAME = "ids";
        public static final String TAG = "tag";
        public static final String USER_IDS = "user_ids";
        public static final String CONTACT_IDS = "contact_ids";
        public static final String TRANSACTION_IDS = "transaction_ids";
    }
}
