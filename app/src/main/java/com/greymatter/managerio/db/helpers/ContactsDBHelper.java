package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.ContactContract.*;
import com.greymatter.managerio.objects.Contact;
import java.util.List;

public class ContactsDBHelper extends ADBHelper<Contact> {
    private static final String[] columnNames = {ContactEntry._ID, ContactEntry.OWNING_USER_ID, ContactEntry.CONTACT_FIRST_NAME,
            ContactEntry.CONTACT_LAST_NAME, ContactEntry.CONTACT_MOBILE_CC, ContactEntry.CONTACT_MOBILE_NO};

    public void insert(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(columnNames[1], contact.getOwningUser().getId());
        cv.put(columnNames[2], contact.getFirstName());
        cv.put(columnNames[3], contact.getLastName());
        cv.put(columnNames[4], contact.getCountryCode());
        cv.put(columnNames[5], contact.getMobileNo());

        DBServices.getWritableDB().insert(ContactEntry.TABLE_NAME, null, cv);
    }

    @Override
    public String[] getTableColumns() {
        return columnNames;
    }

    @Override
    public String getTableName() {
        return ContactEntry.TABLE_NAME;
    }

    @Override
    public Contact createObjectFromCursor(Cursor cursor) throws IllegalArgumentException {
        Contact c = new Contact();
        c.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ContactEntry._ID)));
        c.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_FIRST_NAME)));
        c.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_LAST_NAME)));
        c.setCountryCode(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_MOBILE_CC)));
        c.setMobileNo(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_MOBILE_NO)));
        int owingUserId = getInt(cursor, ContactEntry.OWNING_USER_ID);
        c.setOwningUser(DBServices.getUsersDBHelper().getWithId(owingUserId).get(0));
        return c;
    }

    public List<Contact> getAll() {
        ContentValues cv = new ContentValues();
        return get(cv);
    }

    public List<Contact> getWithId(int id) {
        ContentValues cv = new ContentValues();
        cv.put(ContactEntry._ID, id);
        return get(cv);
    }

    public List<Contact> getAllOwnedBy(int userId) {
        ContentValues cv = new ContentValues();
        cv.put(ContactEntry.OWNING_USER_ID, userId);
        return get(cv);
    }
}
