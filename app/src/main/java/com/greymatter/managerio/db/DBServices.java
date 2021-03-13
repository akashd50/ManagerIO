package com.greymatter.managerio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greymatter.managerio.db.helpers.DBOpenHelper;

public class DBServices {
    private static DBOpenHelper dbOpenHelper;
    private static SQLiteDatabase writableDB, readableDB;
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

    public static void close() {
        dbOpenHelper.close();
    }

}