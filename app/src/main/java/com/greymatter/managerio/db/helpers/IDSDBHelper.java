package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;

import com.greymatter.managerio.AppServices;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.ContactContract.ContactEntry;
import com.greymatter.managerio.db.contracts.IDSContract;
import com.greymatter.managerio.objects.Contact;
import com.greymatter.managerio.objects.IDS;

import java.util.List;

public class IDSDBHelper extends ADBHelper<IDS> {
    private static final String[] columnNames = {IDSContract.IDSEntry.TAG, IDSContract.IDSEntry.USER_IDS,
            IDSContract.IDSEntry.CONTACT_IDS, IDSContract.IDSEntry.TRANSACTION_IDS};
    private static final String TAG = "admin";
    public IDSDBHelper() {
        initializeIDS();
    }

    private void initializeIDS() {
        List<IDS> ids = getAll();
        if (ids.size() == 0) {
            // initialize the ids
            IDS idsIns = new IDS();
            idsIns.setTag(TAG);
            insert(idsIns);
        }
    }

    public void insert(IDS ids) {
        ContentValues cv = new ContentValues();
        cv.put(columnNames[0], ids.getTag());
        cv.put(columnNames[1], ids.getUserId());
        cv.put(columnNames[2], ids.getContactId());
        cv.put(columnNames[3], ids.getTransactionId());

        DBServices.getWritableDB().insert(IDSContract.IDSEntry.TABLE_NAME, null, cv);
    }

    @Override
    public String[] getTableColumns() {
        return columnNames;
    }

    @Override
    public String getTableName() {
        return IDSContract.IDSEntry.TABLE_NAME;
    }

    @Override
    public IDS createObjectFromCursor(Cursor cursor) throws IllegalArgumentException {
        IDS ids = new IDS();
        ids.setTag(getString(cursor, IDSContract.IDSEntry.TAG));
        ids.setUserId(getInt(cursor, IDSContract.IDSEntry.USER_IDS));
        ids.setContactId(getInt(cursor, IDSContract.IDSEntry.CONTACT_IDS));
        ids.setTransactionId(getInt(cursor, IDSContract.IDSEntry.TRANSACTION_IDS));
        return ids;
    }

    public int getNextUserId() {
        int userId = getAll().get(0).getUserId();
        ContentValues where = new ContentValues();
        where.put(IDSContract.IDSEntry.TAG, TAG);

        ContentValues update = new ContentValues();
        update.put(IDSContract.IDSEntry.USER_IDS, userId+1);

        this.update(where, update);
        return userId;
    }

    public int getNextContactId() {
        int contactId = getAll().get(0).getContactId();
        ContentValues where = new ContentValues();
        where.put(IDSContract.IDSEntry.TAG, TAG);

        ContentValues update = new ContentValues();
        update.put(IDSContract.IDSEntry.CONTACT_IDS, contactId+1);

        this.update(where, update);
        return contactId;
    }

    public int getNextTransactionId() {
        int transactionId = getAll().get(0).getTransactionId();
        ContentValues where = new ContentValues();
        where.put(IDSContract.IDSEntry.TAG, TAG);

        ContentValues update = new ContentValues();
        update.put(IDSContract.IDSEntry.TRANSACTION_IDS, transactionId+1);

        this.update(where, update);
        return transactionId;
    }

    public List<IDS> getAll() {
        ContentValues cv = new ContentValues();
        cv.put(IDSContract.IDSEntry.TAG, TAG);
        return get(cv);
    }
}
