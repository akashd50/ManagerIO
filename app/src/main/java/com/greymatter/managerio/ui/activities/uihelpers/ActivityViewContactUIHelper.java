package com.greymatter.managerio.ui.activities.uihelpers;

import android.app.Dialog;
import android.widget.DatePicker;
import android.widget.EditText;
import com.greymatter.managerio.AppServices;
import com.greymatter.managerio.R;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.objects.Contact;
import com.greymatter.managerio.objects.Transaction;
import java.time.LocalDateTime;

public class ActivityViewContactUIHelper {
    public static Transaction tryAndAddTransactionFromDialog(Contact currentContact,
                                                             Dialog dialogWindow) throws Exception {
        Transaction t = new Transaction();
        EditText amount = dialogWindow.findViewById(R.id.add_new_transaction_amount);
        DatePicker datePicker = dialogWindow.findViewById(R.id.add_new_transaction_date);

        t.setAmount(Float.parseFloat(amount.getText().toString()));
        t.setTransactionDateTime(LocalDateTime.of(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), 0, 0));
        t.setOwningUser(AppServices.getActiveUser());
        t.setAssociatedContact(currentContact);

        TransactionsValidator.validateTransaction(t);
        DBServices.getTransactionDBHelper().insert(t);

        return t;
    }
}

