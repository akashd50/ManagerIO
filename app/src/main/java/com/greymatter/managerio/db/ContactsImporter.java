package com.greymatter.managerio.db;

import android.app.Activity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.provider.ContactsContract;
import com.greymatter.managerio.AppServices;
import com.greymatter.managerio.objects.Contact;

public class ContactsImporter {
    public static CursorLoader generateQuery(Activity activity) {
        final String[] PROJECTION =
                {
                        ContactsContract.CommonDataKinds.Phone._ID,
                        ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                };
        return new android.content.CursorLoader(
                activity,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PROJECTION,
                "",
                null,
                null
        );
    }

    public static void importContacts(Cursor contactsResult) {
        int importCount = 0;
        while(contactsResult.moveToNext()) {
            String name = contactsResult.getString(contactsResult.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
            String number = contactsResult.getString(contactsResult.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            System.out.println("IMPORT: " + name + " - " +number);

            Contact toInsert = constructContact(name, number);
            DBServices.getContactsDBHelper().insert(toInsert);
            System.out.println(toInsert);

            importCount++;
        }
        System.out.println("NUMBER OF CONTACTS IMPORT: " + importCount);
    }

    private static Contact constructContact(String name, String number) {
        Contact c = new Contact();
        number = clearString(number);

        int spaceIndex = name.indexOf(' ');
        if (spaceIndex > 0) {
            c.setFirstName(name.substring(0, spaceIndex));
            c.setLastName(name.substring(spaceIndex + 1));
        }else{
            c.setFirstName(name);
        }

        if (number.length() > 10) {
            int ccEndIndex = number.length() - 10;
            c.setCountryCode(number.substring(0, ccEndIndex));
            c.setMobileNo(number.substring(ccEndIndex));
        } else {
            c.setMobileNo(number);
        }

        c.setOwningUser(AppServices.getActiveUser());
        return c;
    }

    private static String clearString(String data) {
        final StringBuilder toReturn = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if(c != ' ' && c != '('
            && c != ')' && c != '-') {
                toReturn.append(c);
            }
        }
        return toReturn.toString();
    }


}
