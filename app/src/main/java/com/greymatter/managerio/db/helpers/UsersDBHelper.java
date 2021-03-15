package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.AppUserContract.*;
import com.greymatter.managerio.objects.AppUser;
import java.util.ArrayList;
import java.util.List;

public class UsersDBHelper {
    private static final String[] columnNames = {AppUserEntry._ID, AppUserEntry.USERNAME, AppUserEntry.USER_EMAIL};

    public static void insert(AppUser user) {
        ContentValues cv = new ContentValues();
        cv.put(AppUserEntry.USERNAME, user.getUsername());
        cv.put(AppUserEntry.USER_EMAIL, user.getEmail());

        DBServices.getWritableDB().insert(AppUserEntry.TABLE_NAME, null, cv);
    }

    public static List<AppUser> getWithId(int id) {
        String[] params = {Integer.toString(id)};
        Cursor cursor = DBServices.getReadableDB().rawQuery("select * from " + AppUserEntry.TABLE_NAME + " where _id=?", params);
        return parseCursorData(cursor);
    }

    private static List<AppUser> get(ContentValues params) {
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
                AppUserEntry.TABLE_NAME,             // The table to query
                columnNames,             // The array of columns to return (pass null to get all)
                selection.toString(),              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                null           // The sort order
        );

        return parseCursorData(cursor);
    }

    private static List<AppUser> parseCursorData(Cursor cursor) {
        ArrayList<AppUser> users = new ArrayList<>();
        while(!cursor.isLast()) {
            cursor.moveToNext();
            AppUser user = new AppUser();
            try{
                user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(AppUserEntry._ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(AppUserEntry.USERNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(AppUserEntry.USER_EMAIL)));
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            users.add(user);
        }
        return users;
    }
}
