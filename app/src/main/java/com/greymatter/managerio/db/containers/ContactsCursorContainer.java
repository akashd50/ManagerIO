package com.greymatter.managerio.db.containers;

import android.database.Cursor;

import com.greymatter.managerio.db.contracts.ContactContract.*;

public class ContactsCursorContainer {
    private final Cursor cursor;
    public ContactsCursorContainer(Cursor cursor) {
        this.cursor = cursor;
    }

    public String getContactFirstName() {
        return cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_FIRST_NAME));
    }

    public String getContactLastName() {
        return cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_LAST_NAME));
    }

    public String getID() {
        return cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry._ID));
    }

    public boolean moveToNext() {
        return cursor.moveToNext();
    }
}
