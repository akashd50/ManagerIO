package com.greymatter.managerio.db.contracts;

import android.provider.BaseColumns;

public class AppUserContract {
    public static class AppUserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String USERNAME = "username";
        public static final String USER_EMAIL = "user_email";
    }
}
