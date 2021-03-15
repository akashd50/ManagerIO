package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;

import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.TransactionContract.*;
import com.greymatter.managerio.objects.Contact;
import com.greymatter.managerio.objects.Transaction;

import java.util.ArrayList;
import java.util.List;


public class TransactionDBHelper {
    private static final String[] columnNames = {TransactionEntry._ID, TransactionEntry.OWNING_USER_ID,
            TransactionEntry.ASSOCIATED_CONTACT_ID, TransactionEntry.TRANSACTION_AMOUNT,
            TransactionEntry.TRANSACTION_DATETIME};

    public static void insert(Transaction transaction) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry.OWNING_USER_ID, "1");
        cv.put(TransactionEntry.ASSOCIATED_CONTACT_ID, transaction.getContactID());
        cv.put(TransactionEntry.TRANSACTION_AMOUNT, transaction.getAmount());
        cv.put(TransactionEntry.TRANSACTION_DATETIME, transaction.getTransactionDateTime().toString());

        DBServices.getWritableDB().insert(TransactionEntry.TABLE_NAME, null, cv);
    }

    public static List<Transaction> getAll() {
        Cursor cursor = DBServices.getReadableDB().rawQuery("select * from " + TransactionEntry.TABLE_NAME + "", null);
        return parseCursorData(cursor);
    }

    public static List<Transaction> getWithId(int id) {
        String[] params = {Integer.toString(id)};
        Cursor cursor = DBServices.getReadableDB().rawQuery("select * from " + TransactionEntry.TABLE_NAME + " where _id=?", params);
        return parseCursorData(cursor);
    }

    public static List<Transaction> getAllForContact(int aContactId) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry.ASSOCIATED_CONTACT_ID, aContactId);
        return get(cv);
    }

    public static List<Transaction> getAllForUser(int aUserId) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry.OWNING_USER_ID, aUserId);
        return get(cv);
    }

    private static List<Transaction> get(ContentValues params) {
        StringBuilder selection = new StringBuilder();
        String[] selectionArgs = new String[params.size()];

        int argsIndex = 0;
        for(String s : columnNames) {
            if(params.get(s) != null) {
                if(argsIndex==selectionArgs.length-1) {
                    selection.append(s).append(" = ?");
                }else {
                    selection.append(s).append(" = ? AND ");
                }
                selectionArgs[argsIndex++] = (String)params.get(s);
            }
        }

        Cursor cursor = DBServices.getReadableDB().query(
                TransactionEntry.TABLE_NAME,             // The table to query
                columnNames,             // The array of columns to return (pass null to get all)
                selection.toString(),              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                null           // The sort order
        );

        return parseCursorData(cursor);
    }

    private static List<Transaction> parseCursorData(Cursor cursor) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        while(!cursor.isLast()) {
            cursor.moveToNext();
            Transaction t = new Transaction();
            try{
                t.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TransactionEntry._ID)));
                t.setAmount(cursor.getFloat(cursor.getColumnIndexOrThrow(TransactionEntry.TRANSACTION_AMOUNT)));
                t.setContactID(cursor.getInt(cursor.getColumnIndexOrThrow(TransactionEntry.ASSOCIATED_CONTACT_ID)));
                t.setTransactionDateTime(cursor.getString(cursor.getColumnIndexOrThrow(TransactionEntry.TRANSACTION_DATETIME)));
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            transactions.add(t);
        }
        return transactions;
    }
}
