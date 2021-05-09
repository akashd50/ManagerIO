package com.greymatter.managerio.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greymatter.managerio.R;
import com.greymatter.managerio.db.DBServices;
import com.greymatter.managerio.db.helpers.ContactsDBHelper;
import com.greymatter.managerio.objects.Contact;
import com.greymatter.managerio.ui.contacts.uihelpers.ContactsValidator;

import java.util.List;

public class ActivityViewContact extends AppCompatActivity {
    private TextView initialsView;
    private EditText firstNameField, lastNameField, mobileNoField;
    private FloatingActionButton addTransactionButton;
    private long currentContactId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        currentContactId = getIntent().getLongExtra("contact_id", -1);

        firstNameField = findViewById(R.id.edit_contact_first_name);
        lastNameField = findViewById(R.id.edit_contact_last_name);
        mobileNoField = findViewById(R.id.edit_contact_mobile_no);
        initialsView = findViewById(R.id.edit_contact_initials);
        addTransactionButton = findViewById(R.id.add_new_transaction_floating_button);
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
                case R.id.add_new_transaction_floating_button:

                    break;
            }
        }
    };

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