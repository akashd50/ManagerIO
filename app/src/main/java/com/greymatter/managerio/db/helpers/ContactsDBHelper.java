package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;

import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.containers.ContactsCursorContainer;
import com.greymatter.managerio.db.contracts.ContactContract.*;
import com.greymatter.managerio.objects.Contact;

import java.util.ArrayList;
import java.util.List;


public class ContactsDBHelper {
    private static final String[] columnNames = {ContactEntry._ID, ContactEntry.CONTACT_FIRST_NAME,
            ContactEntry.CONTACT_LAST_NAME, ContactEntry.CONTACT_MOBILE_NO};

    public static void insert(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(columnNames[1], contact.getFirstName());
        cv.put(columnNames[2], contact.getLastName());
        cv.put(columnNames[3], contact.getMobileNo());

        DBServices.getWritableDB().insert(ContactEntry.TABLE_NAME, null, cv);
    }

    public static List<Contact> getAll() {
        Cursor cursor = DBServices.getReadableDB().rawQuery("select * from " + ContactEntry.TABLE_NAME + "", null);
        return parseCursorData(cursor);
    }

    public static List<Contact> get(ContentValues params) {
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
                ContactEntry.TABLE_NAME,             // The table to query
                columnNames,             // The array of columns to return (pass null to get all)
                selection.toString(),              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                null           // The sort order
        );

        return parseCursorData(cursor);
    }

    private static List<Contact> parseCursorData(Cursor cursor) {
        ArrayList<Contact> contacts = new ArrayList<>();
        while(!cursor.isLast()) {
            cursor.moveToNext();
            Contact c = new Contact();
            try{
                c.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ContactEntry._ID)));
                c.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_FIRST_NAME)));
                c.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_LAST_NAME)));
                c.setMobileNo(cursor.getString(cursor.getColumnIndexOrThrow(ContactEntry.CONTACT_MOBILE_NO)));
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            contacts.add(c);
        }
        return contacts;
    }

    public static void deleteWhereIdGreaterThan(int id) {
        String selection = "_id >= ?";
        String[] selectionArgs = {id+""};
        DBServices.getWritableDB().delete(ContactEntry.TABLE_NAME, selection, selectionArgs);
    }

}
