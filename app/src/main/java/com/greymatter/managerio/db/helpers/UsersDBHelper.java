package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.AppUserContract;
import com.greymatter.managerio.db.contracts.AppUserContract.*;
import com.greymatter.managerio.db.contracts.IDSContract;
import com.greymatter.managerio.objects.AObject;
import com.greymatter.managerio.objects.AppUser;
import java.util.ArrayList;
import java.util.List;

public class UsersDBHelper extends ADBHelper<AppUser> {
    private static final String[] columnNames = {AppUserEntry._ID, AppUserEntry.USERNAME, AppUserEntry.USER_EMAIL, AppUserEntry.CONTACTS_IMPORTED};

    public UsersDBHelper() {
        super();
    }

    @Override
    public String[] getTableColumns() {
        return columnNames;
    }

    @Override
    public String getTableName() {
        return AppUserEntry.TABLE_NAME;
    }

    @Override
    public AppUser createObjectFromCursor(Cursor cursor) throws IllegalArgumentException {
        AppUser user = new AppUser();
        user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(AppUserEntry._ID)));
        user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(AppUserEntry.USERNAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(AppUserEntry.USER_EMAIL)));
        user.setContactsImported(getInt(cursor, AppUserEntry.CONTACTS_IMPORTED) == 1);
        return user;
    }

    public boolean insert(AppUser user) {
        ContentValues cv = new ContentValues();
        cv.put(AppUserEntry._ID, DBServices.getIDSDBHelper().getNextUserId());
        cv.put(AppUserEntry.USERNAME, user.getUsername());
        cv.put(AppUserEntry.USER_EMAIL, user.getEmail());
        cv.put(AppUserEntry.CONTACTS_IMPORTED, user.isContactsImported()? 1 : 0);

        return DBServices.getWritableDB().insert(AppUserEntry.TABLE_NAME, null, cv) != -1;
    }

    public void update(AppUser user) {
        ContentValues where = new ContentValues();
        where.put(AppUserEntry._ID, user.getId());

        ContentValues update = new ContentValues();
        update.put(AppUserEntry.USER_EMAIL, user.getEmail());
        update.put(AppUserEntry.USERNAME, user.getUsername());
        update.put(AppUserEntry.CONTACTS_IMPORTED, user.isContactsImported()? 1: 0);

        this.update(where, update);
    }

    public List<AppUser> getWithId(int id) {
        ContentValues cv = new ContentValues();
        cv.put(AppUserEntry._ID, id);
        return get(cv);
    }

    public List<AppUser> getWithEmail(String email) {
        ContentValues cv = new ContentValues();
        cv.put(AppUserEntry.USER_EMAIL, email);
        return get(cv);
    }

    public boolean contains(AppUser user) {
        ContentValues cv = new ContentValues();
        cv.put(AppUserEntry.USER_EMAIL, user.getEmail());
        return get(cv).size() == 1;
    }
}
