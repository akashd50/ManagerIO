package com.greymatter.managerio.db.contracts;

import android.provider.BaseColumns;

public class ContactContract {
    public static class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String OWNING_USER_ID = "owning_user_id";
        public static final String CONTACT_FIRST_NAME = "contact_first_name";
        public static final String CONTACT_LAST_NAME = "contact_last_name";
        public static final String CONTACT_MOBILE_CC = "contact_mobile_cc";
        public static final String CONTACT_MOBILE_NO = "contact_mobile_no";
    }
}
