package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.TransactionContract.*;
import com.greymatter.managerio.objects.Transaction;
import java.util.List;

public class TransactionDBHelper extends ADBHelper<Transaction> {
    private static final String[] columnNames = {TransactionEntry._ID, TransactionEntry.OWNING_USER_ID,
            TransactionEntry.ASSOCIATED_CONTACT_ID, TransactionEntry.TRANSACTION_AMOUNT,
            TransactionEntry.TRANSACTION_DATETIME};

    @Override
    public String[] getTableColumns() {
        return columnNames;
    }

    @Override
    public String getTableName() {
        return TransactionEntry.TABLE_NAME;
    }

    public void insert(Transaction transaction) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry._ID, DBServices.getIDSDBHelper().getNextTransactionId());
        cv.put(TransactionEntry.OWNING_USER_ID, transaction.getOwningUser().getId());
        cv.put(TransactionEntry.ASSOCIATED_CONTACT_ID, transaction.getAssociatedContact().getId());
        cv.put(TransactionEntry.TRANSACTION_AMOUNT, transaction.getAmount());
        cv.put(TransactionEntry.TRANSACTION_DATETIME, transaction.getTransactionDateTime().toString());

        DBServices.getWritableDB().insert(TransactionEntry.TABLE_NAME, null, cv);
    }

    @Override
    public Transaction createObjectFromCursor(Cursor cursor) throws IllegalArgumentException {
        Transaction t = new Transaction();
        t.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TransactionEntry._ID)));
        t.setAmount(cursor.getFloat(cursor.getColumnIndexOrThrow(TransactionEntry.TRANSACTION_AMOUNT)));
        t.setTransactionDateTime(cursor.getString(cursor.getColumnIndexOrThrow(TransactionEntry.TRANSACTION_DATETIME)));

        int owingUserId = getInt(cursor, TransactionEntry.OWNING_USER_ID);
        int associatedContactId = getInt(cursor, TransactionEntry.ASSOCIATED_CONTACT_ID);
        t.setOwningUser(DBServices.getUsersDBHelper().getWithId(owingUserId).get(0));
        t.setAssociatedContact(DBServices.getContactsDBHelper().getWithId(associatedContactId).get(0));
        return t;
    }

    public List<Transaction> getAll() {
        ContentValues cv = new ContentValues();
        return get(cv);
    }

    public List<Transaction> getWithId(int id) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry._ID, id);
        return get(cv);
    }

    public List<Transaction> getAllForContact(int aContactId) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry.ASSOCIATED_CONTACT_ID, aContactId);
        return get(cv);
    }

    public List<Transaction> getAllForUser(int aUserId) {
        ContentValues cv = new ContentValues();
        cv.put(TransactionEntry.OWNING_USER_ID, aUserId);
        return get(cv);
    }
}
