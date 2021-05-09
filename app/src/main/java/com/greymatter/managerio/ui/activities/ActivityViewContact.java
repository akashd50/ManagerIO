package com.greymatter.managerio.ui.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greymatter.managerio.R;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.objects.Contact;

import java.util.List;

public class ActivityViewContact extends AppCompatActivity {
    private TextView initialsView;
    private EditText firstNameField, lastNameField, mobileNoField;
    private FloatingActionButton addTransactionButton;
    private Dialog addTransactionDialog;
    private long currentContactId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentContactId = getIntent().getLongExtra("contact_id", -1);

        firstNameField = findViewById(R.id.activity_view_contact_first_name);
        lastNameField = findViewById(R.id.activity_view_contact_last_name);
        mobileNoField = findViewById(R.id.activity_view_contact_mobile_no);
        initialsView = findViewById(R.id.activity_view_contact_initials);
        addTransactionButton = findViewById(R.id.activity_view_contact_add_new_transaction_button);
        addTransactionButton.setOnClickListener(onClickListener);

        if (currentContactId != -1) {
            updateInitialsView();
            firstNameField.setEnabled(false);
            lastNameField.setEnabled(false);
            mobileNoField.setEnabled(false);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.activity_view_contact_add_new_transaction_button:
                    showAddNewTransactionDialog();
                    break;
                case R.id.add_transaction_dialog_done:
                    //addNewContact();
                    break;
                case R.id.add_transaction_dialog_cancel:
                    addTransactionDialog.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    private void showAddNewTransactionDialog() {
        addTransactionDialog  = new Dialog(ActivityViewContact.this);
        addTransactionDialog.setCancelable(false);
        addTransactionDialog.setContentView(R.layout.add_new_transaction_dialog);
        addTransactionDialog.findViewById(R.id.add_transaction_dialog_done).setOnClickListener(onClickListener);
        addTransactionDialog.findViewById(R.id.add_transaction_dialog_cancel).setOnClickListener(onClickListener);
        addTransactionDialog.create();
        addTransactionDialog.show();
    }


    private void updateInitialsView() {
        List<Contact> queryResult = DBServices.getContactsDBHelper().getWithId((int)currentContactId);
        if (queryResult.size() > 0) {
            Contact c = queryResult.get(0);
            firstNameField.setText(c.getFirstName());
            lastNameField.setText(c.getLastName());
            mobileNoField.setText(c.getMobileNo());

            String initials = c.getFirstName().toUpperCase().charAt(0) + ""
                                + c.getLastName().toUpperCase().charAt(0);
            initialsView.setText(initials);
        }
    }
}