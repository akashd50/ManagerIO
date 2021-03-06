package com.greymatter.managerio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greymatter.managerio.db.helpers.ContactsDBHelper;
import com.greymatter.managerio.db.helpers.DBOpenHelper;
import com.greymatter.managerio.db.helpers.IDSDBHelper;
import com.greymatter.managerio.db.helpers.TransactionDBHelper;
import com.greymatter.managerio.db.helpers.UsersDBHelper;

public class DBServices {
    private static DBOpenHelper dbOpenHelper;
    private static SQLiteDatabase writableDB, readableDB;
    private static ContactsDBHelper contactsDBHelper;
    private static UsersDBHelper usersDBHelper;
    private static TransactionDBHelper transactionDBHelper;
    private static IDSDBHelper idsdbHelper;
    public static void initDB(Context context) {
        dbOpenHelper = new DBOpenHelper(context);
    }

    public static DBOpenHelper getDbOpenHelper() {
        return dbOpenHelper;
    }

    public static SQLiteDatabase getReadableDB() {
        if(readableDB==null) {
            readableDB = dbOpenHelper.getReadableDatabase();
        }
        return readableDB;
    }

    public static SQLiteDatabase getWritableDB() {
        if(writableDB==null) {
            writableDB = dbOpenHelper.getWritableDatabase();
        }
        return writableDB;
    }

    public static ContactsDBHelper getContactsDBHelper() {
        if(contactsDBHelper==null) {
            contactsDBHelper = new ContactsDBHelper();
        }
        return contactsDBHelper;
    }

    public static UsersDBHelper getUsersDBHelper() {
        if(usersDBHelper==null) {
            usersDBHelper = new UsersDBHelper();
        }
        return usersDBHelper;
    }

    public static TransactionDBHelper getTransactionDBHelper() {
        if(transactionDBHelper==null) {
            transactionDBHelper = new TransactionDBHelper();
        }
        return transactionDBHelper;
    }

    public static IDSDBHelper getIDSDBHelper() {
        if(idsdbHelper==null) {
            idsdbHelper = new IDSDBHelper();
        }
        return idsdbHelper;
    }

    public static void close() {
        dbOpenHelper.close();
    }

}
