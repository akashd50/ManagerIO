package com.greymatter.managerio.db;

import com.greymatter.managerio.db.contracts.ContactContract.*;

public class SQLTables {
    public static final String SQL_CREATE_CONTACTS_TABLE =
            "create table if not exists " + ContactEntry.TABLE_NAME + " (" +
            ContactEntry._ID + " integer primary key autoincrement," +
            ContactEntry.CONTACT_FIRST_NAME + " text," +
            ContactEntry.CONTACT_LAST_NAME + " text," +
            ContactEntry.CONTACT_MOBILE_NO + " text)";

    public static final String SQL_DELETE_CONTACTS_TABLE =
            "drop table if exists " + ContactEntry.TABLE_NAME;
}
