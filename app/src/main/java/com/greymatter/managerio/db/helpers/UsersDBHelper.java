package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.AppUserContract.*;
import com.greymatter.managerio.objects.AObject;
import com.greymatter.managerio.objects.AppUser;
import java.util.ArrayList;
import java.util.List;

public class UsersDBHelper extends ADBHelper<AppUser> {
    private static final String[] columnNames = {AppUserEntry._ID, AppUserEntry.USERNAME, AppUserEntry.USER_EMAIL};

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
        return user;
    }

    public void insert(AppUser user) {
        ContentValues cv = new ContentValues();
        cv.put(AppUserEntry.USERNAME, user.getUsername());
        cv.put(AppUserEntry.USER_EMAIL, user.getEmail());

        DBServices.getWritableDB().insert(AppUserEntry.TABLE_NAME, null, cv);
    }

    public List<AppUser> getWithId(int id) {
        String[] params = {Integer.toString(id)};

        ContentValues cv = new ContentValues();
        cv.put(AppUserEntry._ID, id);
        return get(cv);
    }
}
