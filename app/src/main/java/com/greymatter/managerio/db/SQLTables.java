package com.greymatter.managerio.db;

import com.greymatter.managerio.db.contracts.ContactContract.*;
import com.greymatter.managerio.db.contracts.IDSContract;
import com.greymatter.managerio.db.contracts.TransactionContract.*;
import com.greymatter.managerio.db.contracts.AppUserContract.*;

public class SQLTables {
    public static final String SQL_CREATE_IDS_TABLE =
            "create table if not exists " + IDSContract.IDSEntry.TABLE_NAME + " (" +
                    IDSContract.IDSEntry.TAG + " text," +
                    IDSContract.IDSEntry.USER_IDS + " integer," +
                    IDSContract.IDSEntry.CONTACT_IDS + " integer," +
                    IDSContract.IDSEntry.TRANSACTION_IDS + " integer)";

    public static final String SQL_DELETE_IDS_TABLE =
            "drop table if exists " + IDSContract.IDSEntry.TABLE_NAME;

    public static final String SQL_CREATE_APP_USER_TABLE =
            "create table if not exists " + AppUserEntry.TABLE_NAME + " (" +
                    AppUserEntry._ID + " integer primary key," +
                    AppUserEntry.USERNAME + " text," +
                    AppUserEntry.USER_EMAIL + " text)";

    public static final String SQL_DELETE_APP_USER_TABLE =
            "drop table if exists " + TransactionEntry.TABLE_NAME;

    public static final String SQL_CREATE_CONTACTS_TABLE =
            "create table if not exists " + ContactEntry.TABLE_NAME + " (" +
            ContactEntry._ID + " integer primary key," +
            ContactEntry.OWNING_USER_ID + " integer," +
            ContactEntry.CONTACT_FIRST_NAME + " text," +
            ContactEntry.CONTACT_LAST_NAME + " text," +
            ContactEntry.CONTACT_MOBILE_CC + " text," +
            ContactEntry.CONTACT_MOBILE_NO + " text)";

    public static final String SQL_DELETE_CONTACTS_TABLE =
            "drop table if exists " + ContactEntry.TABLE_NAME;

    public static final String SQL_CREATE_TRANSACTIONS_TABLE =
            "create table if not exists " + TransactionEntry.TABLE_NAME + " (" +
                    TransactionEntry._ID + " integer primary key," +
                    TransactionEntry.OWNING_USER_ID + " integer," +
                    TransactionEntry.ASSOCIATED_CONTACT_ID + " integer," +
                    TransactionEntry.TRANSACTION_AMOUNT + " real," +
                    TransactionEntry.TRANSACTION_DATETIME + " text)";

    public static final String SQL_DELETE_TRANSACTIONS_TABLE =
            "drop table if exists " + TransactionEntry.TABLE_NAME;
}
