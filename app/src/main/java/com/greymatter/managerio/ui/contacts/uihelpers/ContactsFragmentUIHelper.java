package com.greymatter.managerio.ui.contacts.uihelpers;

import android.app.Dialog;
import android.widget.EditText;

import com.greymatter.managerio.R;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.helpers.ContactsDBHelper;
import com.greymatter.managerio.objects.Contact;

public class ContactsFragmentUIHelper {
    public static Contact tryAndAddContactFromDialog(Dialog dialogWindow) throws Exception {
        Contact c = new Contact();
        EditText fn = dialogWindow.findViewById(R.id.add_contact_first_name);
        EditText ln = dialogWindow.findViewById(R.id.add_contact_last_name);
        EditText mobile = dialogWindow.findViewById(R.id.add_contact_mobile_no);

        c.setFirstName(fn.getText().toString());
        c.setLastName(ln.getText().toString());
        c.setMobileNo(mobile.getText().toString());

        ContactsValidator.validateContact(c);
        DBServices.getContactsDBHelper().insert(c);

        return c;
    }
}
