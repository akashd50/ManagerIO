package com.greymatter.managerio.db.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.greymatter.managerio.db.SQLTables.*;


public class DBOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "managerio.db";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_IDS_TABLE);
        db.execSQL(SQL_CREATE_APP_USER_TABLE);
        db.execSQL(SQL_CREATE_CONTACTS_TABLE);
        db.execSQL(SQL_CREATE_TRANSACTIONS_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_IDS_TABLE);
        db.execSQL(SQL_DELETE_APP_USER_TABLE);
        db.execSQL(SQL_DELETE_CONTACTS_TABLE);
        db.execSQL(SQL_DELETE_TRANSACTIONS_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
