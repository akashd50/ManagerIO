package com.greymatter.managerio.db.helpers;

import android.content.ContentValues;
import android.database.Cursor;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.contracts.ContactContract;
import java.util.ArrayList;
import java.util.List;

public abstract class ADBHelper<T> {
    public ADBHelper() {}

    public abstract String[] getTableColumns();
    public abstract String getTableName();
    public abstract T createObjectFromCursor(Cursor cursor);

    public int getNextId() {
        int idToReturn = -1;
        String query = "SELECT * FROM SQLITE_SEQUENCE";
        Cursor cursor = DBServices.getReadableDB().rawQuery(query, null);
        while(cursor.moveToNext()) {
            try {
                if(cursor.getString(cursor.getColumnIndex("name")).compareTo(getTableName()) == 0) {
                    idToReturn = Integer.parseInt(cursor.getString(cursor.getColumnIndex("seq")));
                    break;
                }
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
        return idToReturn;
    }

    public List<T> parseCursorData(Cursor cursor) {
        ArrayList<T> objects = new ArrayList<>();
        while(cursor.moveToNext()) {
            try {
                objects.add(createObjectFromCursor(cursor));
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return objects;
    }

    public List<T> get(ContentValues params) {
        StringBuilder selection = new StringBuilder();
        String[] selectionArgs = new String[params.size()];

        int argsIndex = 0;
        for(String s : getTableColumns()) {
            if(params.get(s) != null) {
                if(argsIndex==selectionArgs.length-1) {
                    selection.append(s).append(" = ?");
                }else {
                    selection.append(s).append(" = ? AND ");
                }
                selectionArgs[argsIndex++] = convertToString(params.get(s));
            }
        }

        Cursor cursor = DBServices.getReadableDB().query(
                getTableName(),             // The table to query
                getTableColumns(),          // The array of columns to return (pass null to get all)
                selection.toString(),       // The columns for the WHERE clause
                selectionArgs,              // The values for the WHERE clause
                null,               // don't group the rows
                null,                // don't filter by row groups
                null                // The sort order
        );

        return parseCursorData(cursor);
    }

    private String convertToString(Object object) {
        if (object instanceof Integer) {
            return Integer.toString((Integer)object);
        }else if (object instanceof Long) {
            return Long.toString((Long)object);
        }else if (object instanceof Float) {
            return Float.toString((Float)object);
        }
        return object.toString();
    }
}
